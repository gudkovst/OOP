#include "trit.h"
#include "tritset.h"



TritSet::TritSet(size_t size = 0) {
	elem_size = sizeof(unsigned int);
	elem_capacity = 4 * elem_size;
	size_t num_elem = size / elem_capacity;
	if (size && size % elem_capacity)
		num_elem++;
	data.resize(num_elem, 0);
}

size_t TritSet::capacity() {
	return data.size() * elem_capacity;
}

size_t TritSet::num_unident_elem() {
	size_t num = 0;
	for (int i = data.size() - 1; i >= 0 && !data[i]; i--, num++);
	return num;
}

size_t TritSet::num_unident_trit() {
	size_t num = 0;
	for (int i = capacity() - 1; i >= 0 && (*this)[i] == Unknown; i--, num++);
	return num;
}

void TritSet::shrink() {
	data.resize(data.size() - num_unident_elem());
}

size_t TritSet::cardinality(Trit_value tv) {
	size_t ans = 0;
	size_t n = length();
	for (int i = 0; i < n; i++)
		if ((*this)[i] == tv)
			ans++;
	return ans;
}

std::unordered_map<Trit_value, size_t> TritSet::cardinality() {
	std::unordered_map<Trit_value, size_t> res;
	size_t n = length();
	for (int i = 0; i < n; i++)
		res[(*this)[i]]++;
	return res;
}

size_t TritSet::length() {
	return capacity() - num_unident_trit();
}

void TritSet::trim(size_t lastIndex) {
	size_t k = lastIndex / elem_capacity;
	if (lastIndex && lastIndex % elem_capacity) k++;
	if (k < capacity())
		data.resize(k);
	return;
}

TritSet TritSet::operator~() {
	size_t n = capacity();
	TritSet res(n);
	for (int i = 0; i < n; i++) {
		Trit t = (*this)[i];
		res[i] = ~t;
	}
	return res;
}

TritPlacer TritSet::operator[](int index) {
	return TritPlacer(this, index);
}

TritSet operator&(TritSet& T1, TritSet& T2) {
	Trit t(Unknown);
	size_t len1 = T1.capacity();
	size_t len2 = T2.capacity();
	size_t n = std::max(len1, len2);
	size_t k = std::min(len1, len2);
	TritSet res(n);
	for (int i = 0; i < k; i++) {
		Trit t1 = T1[i], t2 = T2[i];
		res[i] = t1 & t2;
	}
	if (len1 > len2)
		for (int i = k; i < n; i++)
			res[i] = T1[i] & t;
	else
		for (int i = k; i < n; i++)
			res[i] = T2[i] & t;
	return res;
}

TritSet operator|(TritSet& T1, TritSet& T2) {
	Trit t(Unknown);
	size_t len1 = T1.capacity();
	size_t len2 = T2.capacity();
	size_t n = std::max(len1, len2);
	size_t k = std::min(len1, len2);
	TritSet res(n);
	for (int i = 0; i < k; i++) {
		Trit t1 = T1[i], t2 = T2[i];
		res[i] = t1 | t2;
	}
	if (len1 > len2)
		for (int i = k; i < n; i++)
			res[i] = T1[i] | t;
	else
		for (int i = k; i < n; i++)
			res[i] = T2[i] | t;
	return res;
}

TritPlacer::TritPlacer(TritSet* ptr, int ind) {
	tsp = ptr;
	index = ind;
}

TritPlacer::operator Trit_value() {
	size_t ind = index / tsp->elem_capacity;
	size_t pos = index % tsp->elem_capacity;
	unsigned int shift = 2 * (tsp->elem_capacity - pos - 1);
	unsigned int mask = 3 << shift;
	unsigned int tv = (tsp->data[ind] & mask) >> shift;

	if (!tv) return Unknown;
	if (tv == 1) return False;
	if (tv == 3) return True;
}

TritPlacer::operator Trit() {
	return Trit(static_cast<Trit_value>(*this));
}

void TritPlacer::operator=(Trit_value tv) {
	this->operator=(Trit(tv));
}

void TritPlacer::operator=(Trit t) {
	size_t ind = index / tsp->elem_capacity;
	size_t pos = index % tsp->elem_capacity;
	unsigned int shift = 2 * (tsp->elem_capacity - pos - 1);
	if (t == Unknown) {
		if (index < tsp->capacity()) {
			unsigned int mask = ~(3 << shift);
			tsp->data[ind] &= mask;
		} return;
	}
	if (index >= tsp->capacity()) {
		size_t new_size = index / tsp->elem_capacity;
		if (index % tsp->elem_capacity)
			new_size++;
		tsp->data.resize(new_size, 0);
	}
	if (t == True) {
		unsigned int mask = 3 << shift;
		tsp->data[ind] |= mask;
	}
	if (t == False) {
		unsigned int mask = ~(3 << shift);
		tsp->data[ind] &= mask;
		mask = 1 << shift;
		tsp->data[ind] |= mask;
	}
}

void TritPlacer::operator=(TritPlacer tp) {
	Trit t = tp;
	*this = t;
}
