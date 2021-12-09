#include "exceptions.h"
#include <string>

TooMushArgs::TooMushArgs(const std::string& message){
	meserr = "Too mush args for " + message + '\n';
}

const char* TooMushArgs::what(){
	return meserr.c_str();
}

TooFewArgs::TooFewArgs(const std::string& message){
	meserr = "Too few args for " + message + '\n';
}

const char* TooFewArgs::what(){
	return meserr.c_str();
}

FileBeginningError::FileBeginningError(){
	meserr = "File must start with desc!\n";
}

const char* FileBeginningError::what(){
	return meserr.c_str();
}

UnknownWorker::UnknownWorker(){
	meserr = "Discover unknown worker\n";
}

const char* UnknownWorker::what(){
	return meserr.c_str();
}

DelimiterError::DelimiterError(){
	meserr = "Delimiter must be csed\n";
}

const char* DelimiterError::what(){
	return meserr.c_str();
}

DescriptionBlockError::DescriptionBlockError(){
	meserr = "Block description error\n";
}

const char* DescriptionBlockError::what(){
	return meserr.c_str();
}

DescriptionSequenceError::DescriptionSequenceError(){
	meserr = "Sequence description error\n";
}

const char* DescriptionSequenceError::what(){
	return meserr.c_str();
}