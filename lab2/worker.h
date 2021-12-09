#pragma once

#include <iostream>
#include <vector>
#include <string>
#include <fstream>
#include <regex>
#include <algorithm>

#include "exceptions.h"

class Worker {
protected:
	bool input;
	bool output;
public:
	virtual void execute(std::vector<std::string>& text) = 0;
	bool isInput();
	bool isOutput();
};

class WorkerRead : public Worker {
private:
	std::string filename;
public:
	WorkerRead(const std::string& argv);
	void execute(std::vector<std::string>& text) override;
};

class WorkerWrite : public Worker {
private:
	std::string filename;
public:
	WorkerWrite(const std::string& argv);
	void execute(std::vector<std::string>& text) override;
};

class WorkerGrep : public Worker {
private:
	std::string word;
public:
	WorkerGrep(const std::string& argv);
	void execute(std::vector<std::string>& text) override;
};

class WorkerSort : public Worker {
public:
	WorkerSort(const std::string& argv);
	void execute(std::vector<std::string>& text) override;
};

class WorkerReplace : public Worker {
private:
	std::string word1, word2;
public:
	WorkerReplace(const std::string& argv);
	void execute(std::vector<std::string>& text) override;
};

class WorkerDump : public Worker {
private:
	std::string filename;
public:
	WorkerDump(const std::string& argv);
	void execute(std::vector<std::string>& text) override;
};
