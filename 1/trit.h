#pragma once

#include <iostream>

namespace TRIT {

	enum Trit_value {
		Unknown = 0,
		False = 1,
		True = 3
	};

	class Trit {
		Trit_value value;
	public:
		
		Trit(Trit_value tv);

		Trit operator~();
		bool operator==(Trit_value tv);
		void operator=(Trit_value tv);

		friend Trit operator&(const Trit& t1, const Trit& t2);
		friend Trit operator|(const Trit& t1, const Trit& t2);
	};

	Trit operator&(const Trit& t1, const Trit& t2);
	Trit operator|(const Trit& t1, const Trit& t2);
}
