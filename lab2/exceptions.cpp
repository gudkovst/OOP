#include "exceptions.h"

TooMushArgs::TooMushArgs(const std::string& message) {
	meserr = "Too mush args for " + message + '\n';
}

const char* TooMushArgs::what() const noexcept {
	return meserr.c_str();
}

TooFewArgs::TooFewArgs(const std::string& message) {
	meserr = "Too few args for " + message + '\n';
}

const char* TooFewArgs::what() const noexcept {
	return meserr.c_str();
}

FileOpenError::FileOpenError() {
	meserr = "File is not opened.\n";
}

const char* FileOpenError::what() const noexcept {
	return meserr.c_str();
}

FileBeginningError::FileBeginningError() {
	meserr = "File must start with desc!\n";
}

const char* FileBeginningError::what() const noexcept{
	return meserr.c_str();
}

UnknownWorker::UnknownWorker() {
	meserr = "Discover unknown worker\n";
}

const char* UnknownWorker::what() const noexcept {
	return meserr.c_str();
}

DelimiterError::DelimiterError() {
	meserr = "Delimiter must be csed\n";
}

const char* DelimiterError::what() const noexcept {
	return meserr.c_str();
}

DescriptionBlockError::DescriptionBlockError() {
	meserr = "Block description error\n";
}

const char* DescriptionBlockError::what() const noexcept {
	return meserr.c_str();
}

DescriptionSequenceError::DescriptionSequenceError(const std::string& msg) {
	meserr = "Sequence description error" + msg + "\n";
}

const char* DescriptionSequenceError::what() const noexcept {
	return meserr.c_str();
}
