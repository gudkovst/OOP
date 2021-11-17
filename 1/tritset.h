#include <iostream>
#include <vector>

#include "trit.h"


namespace TRIT{

	class TritPlacer;

	class TritSet {
		private:
			std::vector<uint> data;
			size_t elem_size; //размер элем вектора
			size_t elem_capacity; //число тритов в элем вектора

			size_t num_unident_elem(); //число неустановл элем
			size_t num_unident_trit(); //число неустановл тритов
		
		public:
			TritSet(size_t size);
			size_t capacity(); //число тритов в data
			void shrink(); //освобождение пустой памяти
			size_t cardinality(Trit_value tv); //число tv тритов в data
			std::unordered_map<TritValue, size_t> cardinality(); //число тритов каждого значения
			size_t length(); //индекс последнего не Unknown трита +1
			void trim(size_t lastIndex); //забыть содержимое от lastIndex и дальше

			TritSet operator~(const TritSet& T);
			TritPlacer operator[](int index);
		
		friend class TritPlacer;
		friend TritSet operator&(const TritSet& T1,const TritSet& T2);
		friend TritSet operator|(const TritSet& T1,const TritSet& T2);
	};

	TritSet operator&(const TritSet& T1,const TritSet& T2);
	TritSet operator|(const TritSet& T1,const TritSet& T2);

	class TritPlacer {
		private:
			TritSet* tsp;
			int index;

		public:
			TritPlacer(TritSet* ptr, int ind);
			operator TritValue();
			operator Trit();

			void operator=(TritValue tv);
			void operator=(Trit t);
			void operator=(TritPlacer tp);
	};

}
