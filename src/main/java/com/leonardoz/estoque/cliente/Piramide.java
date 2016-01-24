package com.leonardoz.estoque.cliente;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Piramide {
    public static void main(String[] args) {
	criaPiramide(5);
    }

    public static void criaPiramide(int linhas) {
	// IntStream
	// .range(0, linhas)
	// .peek(i -> System.out.println(i))
	// .mapToObj(j -> IntStream.range(0, j))
	// .peek(j -> System.out.print(j))
	// .collect(Collectors.toList());

	List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
	list.get(7);
	list.get(18);
	

    }

}
