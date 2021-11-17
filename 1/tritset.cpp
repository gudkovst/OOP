#include <iostream>


#include "trit.h"
#include "tritset.h"

namespace TRIT{
	Tritset::TritSet(size_t size = 0){
		elem_size = sizeof(uint);
		elem_capacity = 4 * elem_size;
		size_t num_elem = size / elem_capacity;
		if (size && size % elem_capacity)
			num_elem++;
		data.resize(num_elem, 0);
	}

	size_t TritSet::capacity(){
		return data.size() * elem_capacity;
	}

	size_t TritSet::num_unident_elem(){
		size_t num = 0;
		for (int i = data.size() - 1; i >= 0 && !data[i]; i--, num++);
		return num;
	}

	size_t TritSet::num_unident_trit(){
		size_t num = 0;
		for (int i = capacity() - 1; i >= 0 && (*this)[i] == Unknown; i--, num++);
		return num;
	}

	void TritSet::shrink(){
		data.resize(data.size() - num_unident_elem());
	}

	size_t TritSet::cardinality(Trit_value tv){
		size_t ans = 0;
		size_t n = length();
		for (int i = 0; i < n; i++)
			if ((*this)[i] == tv)
				ans++;
		return ans;
	}

	std::unordered_map<TritValue, size_t> TritSet::cardinality(){
		std::unordered_map<TritValue, size_t> res;
		size_t n = length();
		for (int i = 0; i < n; i++)
			res[(*this)[i]]++;
		return res;
	}

	size_t TritSet::length(){
		return capacity() - num_unident_trit();
	}

	void TritSet::trim(size_t lastIndex){
		if (lastIndex < capacity())
			data.resize(lastIndex);
		return;
	}

	TritSet TritSet::operator~(const TritSet& T){
		size_t n = capacity();
		TritSet res(n);
		for (int i = 0; i < n; i++){
			Trit t = (*this)[i];
			res[i] = ~t;
		}
		return res;
	}

	TritPlacer TritSet::operator[](int index){
		return TritPlacer(this, index);
	}

	TritSet operator&(const TritSet& T1,const TritSet& T2){
		size_t len1 = T1.capacity();
		size_t len2 = T2.capacity();
		size_t n = std::max(len1, len2);
		TritSet res(n);
		for (int i = 0; i < n; i++){
			Trit t1, t2;
			if (i < len1) t1 = T1[i];
			if (i < len2) t2 = T2[i];
			res[i] = t1 & t2;
		}
		return res;
	}
	TritSet operator|(const TritSet& T1,const TritSet& T2){
		size_t len1 = T1.capacity();
		size_t len2 = T2.capacity();
		size_t n = std::max(len1, len2);
		TritSet res(n);
		for (int i = 0; i < n; i++){
			Trit t1, t2;
			if (i < len1) t1 = T1[i];
			if (i < len2) t2 = T2[i];
			res[i] = t1 | t2;
		}
		return res;
	}


	TritPlacer::TritPlacer(TritSet* ptr, int ind){
		tsp = ptr;
		index = ind;
	}

	TritPlacer::operator TritValue(){
		size_t ind = index / tsp->elem_capacity;
		size_t pos = index % tsp->elem_capacity;
		uint shift = 2 * (tsp->elem_capacity - pos - 1);
		uint mask = 3 << shift;
		uint tv = (tsp->data[ind] & mask) >> shift;

		if (!tv) return Unknown;
		if (tv == 1) return False;
		if (tv == 3) return True;
	}

	TritPlacer::operator Trit(){
		return Trit(static_cast<TritValue>(*this));
	}

	void TritPlacer::operator=(TritValue tv){
		this->operator=(Trit(tv));
	}

	void TritPlacer::operator=(Trit t){
		size_t ind = index / tsp->elem_capacity;
		size_t pos = index % tsp->elem_capacity;
		uint shift = 2 * (tsp->elem_capacity - pos - 1);
		if (t == Unknown){
			if (index < tsp->capacity()){
				uint mask = ~(3 << shift);
				tsp->data[ind] &= mask;
			} return;
		}
		if (index >= tsp->capacity()){
			size_t new_size = index / tsp->elem_capacity;
			if (index % tsp->elem_capacity)
				new_size++;
			tsp->data.resize(new_size, 0);
		}
		if (t == True){
			uint mask = 3 << shift;
			tsp->data[ind] |= mask;
		}
		if (t == False){
			uint mask = ~(3 << shift);
			tsp->data[ind] &= mask;
			mask = 1 << shift;
			tsp->data[ind] |= mask;
		}
	}

	void TritPlacer::operator=(TritPlacer tp){
		Trit t = tp;
		*this = t;
	}

}
