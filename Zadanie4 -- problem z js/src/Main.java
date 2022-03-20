interface Drawable{
    String draw(String text);

}
public class Main {
    public static void main(String[] args)
    {
        int[] CRC_TABLE = new int[256];
        for (int i = 0; i < 256; i++) {
            int code = i;
            for (int j = 0; j < 8; j++) {
                code = (code & 0x01 ? 0xEDB88320 ^ (code >>> 1) : (code >>> 1));
            }
            CRC_TABLE[i] = code;
        }

        Drawable crc32=(text)->{
        int crc= -1;
        for(int i=0; i<text.length();i++){
            var code = Character.codePointAt(text,i);
            crc = CRC_TABLE[(code ^ crc) & 0xFF] ^ (crc >>> 8);
        }
        return String.valueOf((Math.pow(-1,crc)));
        };
        System.out.println(crc32.draw("This is example text ..."));
    }
}
