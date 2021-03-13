package com.example.demo.mongo.helper;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExampleBuilder {
	public static final ExampleMatcher CONTAINING_ALL = ExampleMatcher.matchingAll().withIgnoreNullValues()
			.withIgnoreCase();
	public static final ExampleMatcher CONTAINING_ANY = ExampleMatcher.matchingAny().withIgnoreNullValues()
			.withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);

	public static <S> Example<S> containingAll(S doc) {
		return of(doc, CONTAINING_ALL);
	}

	public static <S> Example<S> containingAny(S doc) {
		return of(doc, CONTAINING_ANY);
	}

	private static <S> Example<S> of(S doc, ExampleMatcher exampleMatcher) {
		return Example.<S>of(doc, exampleMatcher);
	}
}
