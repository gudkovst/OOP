#include <iostream>
#include "trit.h"

namespace TRIT {
	Trit::Trit(Trit_value tv = Unknown) {
		value = tv;
	}

	Trit Trit::operator&(Trit t1, Trit t2) {
		if (t1.value == False || t2.value == False) return Trit(False);
		if (t1.value == Unknown || t2.value == Unknown) return Trit();
		return Trit(True);
	}

	Trit Trit::operator|(Trit t1, Trit t2){
		if (t1.value == True || t2.value == True) return Trit(True);
		if (t1.value == Unknown || t2.value == Unknown) return Trit();
		return Trit(False);
	}

	Trit Trit::operator~(Trit t){
		if (t.value == Unknown) return Trit();
		if (t.value == False) return Trit(True);
		return Trit(False);
	}

}