#pragma once
#include <stdexcept>
#include <string>

class TooMushArgs : public std::exception {
private:
	std::string meserr;
public:
	TooMushArgs(const std::string& message);
	const char* what() const noexcept override;
};

class TooFewArgs : public std::exception {
private:
	std::string meserr;
public:
	TooFewArgs(const std::string& message);
	const char* what() const noexcept override;
};

class FileOpenError : public std::exception {
private:
	std::string meserr;
public:
	FileOpenError();
	const char* what() const noexcept override;
};

class FileBeginningError : public std::exception {
private:
	std::string meserr;
public:
	FileBeginningError();
	const char* what() const noexcept override;
};

class UnknownWorker : public std::exception {
private:
	std::string meserr;
public:
	UnknownWorker();
	const char* what() const noexcept override;
};

class DelimiterError : public std::exception {
private:
	std::string meserr;
public:
	DelimiterError();
	const char* what() const noexcept override;
};

class DescriptionBlockError : public std::exception {
private:
	std::string meserr;
public:
	DescriptionBlockError();
	const char* what() const noexcept override;
};

class DescriptionSequenceError : public std::exception {
private:
	std::string meserr;
public:
	DescriptionSequenceError(const std::string& msg);
	const char* what() const noexcept override;
};
