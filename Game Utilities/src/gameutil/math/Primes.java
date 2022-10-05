package gameutil.math;

import java.util.ArrayList;

public class Primes {
	static ArrayList<Integer> primes=new ArrayList<>();
	
	public static int getPrime(int n) {
		if (primes.size()>n) {
			return primes.get(n);
		}
		if (primes.size()==n) {
			nextPrime();
			return primes.get(n);
		}
		
		getPrime(n-1);
		nextPrime();
		return primes.get(n);
	}
	
	public static void nextPrime() {
		
		if (primes.size()>0) {
			int k=primes.get(primes.size()-1)+1;
			while (!isPrime(k)) {
				k++;
			}
			primes.add(k);
		} else {
			primes.add(2);
		}
		
	}
	
	public static boolean prime(int k) {
		if (primes.size()>0&&k<=primes.get(primes.size()-1)){
			return primes.contains(k);
		} else {
			nextPrime();
			return prime(k);
		}
	}
	
	private static boolean isPrime(int k) {
		if (k<=primes.get(primes.size()-1)){
			return primes.contains(k);
		}
		for (int i=2;i<=Math.ceil(Math.sqrt(k));i++) {
			if (k%i==0) {
				return false;
			}
		}
		return true;
	}
}
