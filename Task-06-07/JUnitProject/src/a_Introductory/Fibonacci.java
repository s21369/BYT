package a_Introductory;

public class Fibonacci {

	public int fib(int n) {
		return switch (n) {
			case 0 -> 0;
			case 1 -> 1;
			// there was an unnecessary + 1 operation
			default -> fib(n - 1) + fib(n - 2);
		};
	}

}
