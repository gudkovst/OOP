#include <iostream>
#include <vector>

#include "trit.h"


namespace TRIT{

	class Tritset {
	private:
		std::vector<uint> data;
		size_t elem_size;
		size_t elem_capacity;

	public:
		Tritset(size_t size);
		size_t capacity();
		size_t num_unident();
		void shrink();
		size_t cardinality(Trit_value tv);// ещё не умею
		
		
	};


}
