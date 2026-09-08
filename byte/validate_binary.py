import json
import sys
from pathlib import Path

def extract_raw_hex(node) -> str:
    """JSON 구조에서 모든 헥사 문자열을 순서대로 추출합니다."""
    hex_str = ""
    if isinstance(node, dict):
        for k, v in node.items():
            if k.startswith("---"):
                continue
            hex_str += extract_raw_hex(v)
    elif isinstance(node, list):
        for item in node:
            hex_str += extract_raw_hex(item)
    elif isinstance(node, str):
        hex_str += node
    return hex_str

def format_hex_dump(data_bytes: bytes, offset: int, window: int = 16) -> str:
    """주어진 offset 주변 데이터를 보기에 깔끔한 Hex Dump 형태로 변환합니다."""
    start = max(0, offset - 8)
    end = min(len(data_bytes), offset + window)
    
    hex_list = []
    for i in range(start, end):
        byte_val = f"{data_bytes[i]:02X}"
        # 불일치 발생 지점을 눈에 띄게 표시 ([XX])
        if i == offset:
            byte_val = f"[{byte_val}]"
        hex_list.append(byte_val)
        
    return " ".join(hex_list)

def validate_binary_match(json_path: str, orig_class_path: str) -> bool:
    try:
        with open(json_path, "r", encoding="utf-8") as f:
            data = json.load(f)
    except Exception as e:
        print(f"❌ [JSON Read Error] {e}")
        return False

    raw_hex = extract_raw_hex(data)
    try:
        reconstructed_bytes = bytes.fromhex(raw_hex)
    except ValueError as e:
        print(f"❌ [Hex Conversion Error] Invalid hex string in JSON: {e}")
        return False

    try:
        with open(orig_class_path, "rb") as f:
            original_bytes = f.read()
    except Exception as e:
        print(f"❌ [Original Class Read Error] {e}")
        return False

    len_orig = len(original_bytes)
    len_recon = len(reconstructed_bytes)

    # 1. 파일 크기 불일치 체크 및 출력
    size_mismatch = (len_orig != len_recon)

    # 2. 첫 번째 바이트 불일치 오프셋 탐색
    mismatch_offset = None
    min_len = min(len_orig, len_recon)
    for i in range(min_len):
        if original_bytes[i] != reconstructed_bytes[i]:
            mismatch_offset = i
            break

    # 파일 크기는 다른데 앞부분 바이트는 모두 같은 경우 (데이터 잘림/더 붙음)
    if mismatch_offset is None and size_mismatch:
        mismatch_offset = min_len

    # 불일치 발생 시 상세 분석 정보 출력
    if mismatch_offset is not None or size_mismatch:
        print(f"\n❌ [Binary Identity Mismatch] {json_path}")
        print("=" * 60)
        print(f" • Original File Size     : {len_orig} bytes")
        print(f" • Reconstructed Size   : {len_recon} bytes")
        
        if size_mismatch:
            diff_size = len_recon - len_orig
            sign = "+" if diff_size > 0 else ""
            print(f" • Size Difference       : {sign}{diff_size} bytes")

        if mismatch_offset is not None and mismatch_offset < min_len:
            orig_val = original_bytes[mismatch_offset]
            recon_val = reconstructed_bytes[mismatch_offset]
            
            print(f"\n [!] 첫 번째 바이트 불일치 지점 상세:")
            print(f"  - Hex Offset      : {hex(mismatch_offset)} ({mismatch_offset:08X})")
            print(f"  - Decimal Offset  : {mismatch_offset} 바이트 위치")
            print(f"  - Original Byte   : 0x{orig_val:02X} (Dec: {orig_val})")
            print(f"  - Reconstructed   : 0x{recon_val:02X} (Dec: {recon_val})")
            print("-" * 60)
            
            print(" [주변 바이트 덤프 비교 (기준 지점: [XX])]")
            print(f"  Original   : {format_hex_dump(original_bytes, mismatch_offset)}")
            print(f"  Reconstruct: {format_hex_dump(reconstructed_bytes, mismatch_offset)}")
        elif size_mismatch:
            print(f"\n [!] 앞부분 {min_len}바이트는 완전히 일치하나, 파일 끝부분 길이가 다릅니다.")

        print("=" * 60)
        return False

    print(f"✅ Binary Identity PASSED for {json_path} (1:1 Bitwise Equal, {len_orig} bytes)")
    return True

if __name__ == "__main__":
    if len(sys.argv) < 3:
        print("Usage: python validate_binary.py <path_to_json> <path_to_orig_class>")
        sys.exit(1)

    target_json = sys.argv[1]
    orig_class = sys.argv[2]

    if validate_binary_match(target_json, orig_class):
        sys.exit(0)
    else:
        sys.exit(1)