#include <iostream>


#include "trit.h"
#include "tritset.h"

namespace TRIT{
	Tritset::Tritset(size_t size = 0){
		elem_size = sizeof(uint);
		elem_capacity = 4 * elem_size;
		int num_elem = size / elem_capacity;
		if (size % elem_capacity)
			num_elem++;
		data.resize(num_elem, 0);
	}

	size_t Tritset::capacity(){
		return data.size() * elem_capacity;
	}

	size_t Tritset::num_unident_elem(){
		size_t num = 0;
		for (int i = data.size() - 1; i >= 0 && !data[i]; i--, num++);
		return num;
	}

	void Tritset::shrink(){
		data.resize(data.size() - num_unident_elem());
	}

	size_t Tritset::cardinality(Trit_value tv){
		// ещё не умею
	}
}
