package main

import (
	"github.com/stretchr/testify/require"
	"testing"
)

func TestMultiplication(t *testing.T) {
	println("Before tests")
	defer println("After tests have run")
	testcases := []struct {
		name              string
		givenFirstFactor  int
		givenSecondFactor int
		thenExpectResult  int
	}{
		{
			name:              "Two by one is two",
			givenFirstFactor:  2,
			givenSecondFactor: 1,
			thenExpectResult:  2,
		},
		{
			name:              "Two by two is four",
			givenFirstFactor:  2,
			givenSecondFactor: 2,
			thenExpectResult:  4,
		},
		{
			name:              "Three by two is six",
			givenFirstFactor:  3,
			givenSecondFactor: 2,
			thenExpectResult:  6,
		},
	}
	for _, testCase := range testcases {
		t.Run(testCase.name, func(t *testing.T) {
			println("Testing " + testCase.name)
			require.Equal(t, testCase.thenExpectResult, multiply(testCase.givenFirstFactor, testCase.givenSecondFactor))
		})
	}

}
