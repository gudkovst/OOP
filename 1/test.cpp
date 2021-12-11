#include "pch.h"
#include "tritset.h"

class TritSetTest: public ::testing::Test{
protected:
	TritSet T1(10);
	TritSet T2(15);
	TritSet TT(10);
	void SetUp(){
		for (int i = 0; i < 15; i++) {
			if (i < 10) {
				T1[i] = True;
				T2[i] = (i % 2) ? Unknown : False;
			}
			else T2[i] = True;
		}
		TT[1] = True;
	}
};

TEST_F(TritSetTest, AndTest){
	TritSet T3 = T1 & T2;
	EXPECT_EQ(T3.capacity(), 15);
	for (int i = 0; i < 15; i++)
		EXPECT_EQ(T3[i], T2[i]);
}

TEST_F(TritSetTest, OrTest){
	TritSet T3 = T1 | T2;
	EXPECT_EQ(T3.capacity(), 15);
	for (int i = 0; i < 10; i++)
		EXPECT_EQ(T3[i], True);
	for (int i = 10; i < 15; i++)
		EXPECT_EQ(T3[i], Unknown);
}

TEST_F(TritSetTest, NotTest){
	TritSet T3 = ~T2;
	EXPECT_EQ(T3.capacity(), 15);
	for (int i = 0; i < 15; i++){
		if (i % 2) EXPECT_EQ(T3[i], Unknown);
		else EXPECT_EQ(T3[i], True);
	}
}

TEST_F(TritSetTest, CapacityTest){
	EXPECT_EQ(T1.capacity(), 10);
	EXPECT_EQ(T2.capacity(), 15);
	EXPECT_EQ(TT.capacity(), 10);
}

TEST_F(TritSetTest, CardinalityTest){
	TritSet T(26);
	for (int i = 0; i < 26; i++){
		if (!(i % 3)) T[i] = False;
		if (i % 3 == 1) T[i] = True;
		else T[i] = Unknown;
	}
	EXPECT_EQ(T.cardinality(True), 9);
	EXPECT_EQ(T.cardinality(False), 9);
	EXPECT_EQ(T.cardinality(Unknown), 8);
	auto card = T.cardinality();
	EXPECT_EQ(card[True], 9);
	EXPECT_EQ(card[False], 9);
	EXPECT_EQ(card[Unknown], 8);
}

TEST_F(TritSetTest, LengthShrinkTest){
	EXPECT_EQ(TT.capacity(), 10);
	EXPECT_EQ(TT.length(), 2);
	TT.shrink();
	EXPECT_EQ(TT.capacity(), 2);
	EXPECT_EQ(TT.length(), 2);
}

TEST_F(TritSetTest, LengthTrimTest){
	TritSet T = T1 | T2;
	EXPECT_EQ(T.capacity(), 15);
	EXPECT_EQ(T.length(), 10);
	T.trim(11);
	EXPECT_EQ(T.capacity(), 11);
	EXPECT_EQ(T.length(), 10);
	T[7] = Unknown;
	T.trim(8);
	EXPECT_EQ(T.capacity(), 8);
	EXPECT_EQ(T.length(), 7);
}
