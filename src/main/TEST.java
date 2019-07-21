
public class TEST {

	public static void main(String[] args) {
		int x = 0b10010001_00100000_11000000_11111111;
		int green = x >> 8;
		green = green & 0b111111111;
		int blue = x << 8 << 8 << 8 >> 8 >> 8 >> 8;
		int red = x << 8 >> 8 >> 8 >> 8;
		System.out.println(green + "\n"  + blue + "\n" + red +  "\n" );
	}

}
