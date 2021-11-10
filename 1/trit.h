#pragma once

#include <iostream>

namespace TRIT {

	enum Trit_value {
		False = 0,
		Unknown,
		True
	};

	class Trit {
	public:
		Trit_value value;

		Trit(Trit_value tv);

		Trit operator&(Trit t1, Trit t2);
		Trit operator|(Trit t1, Trit t2);
		Trit operator~(Trit t);

	};
}
