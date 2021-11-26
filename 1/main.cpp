#include <iostream>

#include "tritset.h"


int main(){

	TritSet T1(10);
	TritSet T2(15);

	for (int i = 0; i < 15; i++){
		if (i < 10){
			T1[i] = True;
			T2[i] = (i%2)? Unknown : False;
		}
		else T2[i] = True;
	}

	TritSet T3 = T1 & T2;
	TritSet T4 = T1 | T2;
	TritSet T5 = ~T2;

	std::cout<<"T1: "<<T1.capacity()<<'\n'; //10
	std::cout<<"T2: "<<T2.capacity()<<'\n'; //15
	std::cout<<"T3: "<<T3.capacity()<<'\n'; //15
	std::cout<<"T5: "<<T5.capacity()<<'\n'; //15

	std::cout<<"True in T5: "<<T5.cardinality(True); //5

	auto card = T3.cardinality();
	for (auto x: card)
		std::cout<< x.first<<" : "<<x.second<<'\n'; //T-0, F-5, U-10

	std::cout<<T3.length()<<'\n'; //15

	TritSet TT(10);
	TT[1] = True;
	std::cout<<<<TT.capacity()<<' '<<TT.length()<<'\n'; // 10 2
	TT.shrink();
	std::cout<<<<TT.capacity()<<' '<<TT.length()<<'\n'; //2 2

	std::cout<<"T3: "<<T3.capacity()<<' '<<T3.length()<<'\n'; //15 9
	T3.trim(8);
	std::cout<<"T3: "<<T3.capacity()<<' '<<T3.length()<<'\n'; //8 7

	return 0;
}
