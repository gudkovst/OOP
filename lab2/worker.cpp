#include "worker.h"

bool Worker::isInput() {
	return input;
}

bool Worker::isOutput() {
	return output;
}

WorkerRead::WorkerRead(const std::string& argv) : Worker() {
	input = false;
	output = true;
	int i = 0;
	filename.clear();
	for (; argv[i] == ' ' && i < argv.size(); i++);
	if (i == argv.size())
		throw TooFewArgs("Read");
	for (; argv[i] != ' ' && i < argv.size(); i++)
		filename += argv[i];
	if (i < argv.size())
		throw TooMushArgs("Read");
}

void WorkerRead::execute(std::vector<std::string>& text) {
	std::ifstream in(filename);
	while (!in.eof()) {
		std::string buf;
		std::getline(in, buf);
		text.push_back(buf);
	}
	in.close();
}

WorkerWrite::WorkerWrite(const std::string& argv) : Worker() {
	input = true;
	output = false;
	int i = 0;
	filename.clear();
	for (; argv[i] == ' ' && i < argv.size(); i++);
	if (i == argv.size())
		throw TooFewArgs("Write");
	for (; argv[i] != ' ' && i < argv.size(); i++)
		filename += argv[i];
	if (i < argv.size())
		throw TooMushArgs("Write");
}

void WorkerWrite::execute(std::vector<std::string>& text) {
	std::ofstream out(filename);
	for (auto& str : text)
		out << str << '\n';
	text.clear();
	out.close();
}

WorkerGrep::WorkerGrep(const std::string& argv) : Worker() {
	input = true;
	output = true;
	int i = 0;
	word.clear();
	for (; argv[i] == ' ' && i < argv.size(); i++);
	if (i == argv.size())
		throw TooFewArgs("Grep");
	for (; argv[i] != ' ' && i < argv.size(); i++)
		word += argv[i];
	if (i < argv.size())
		throw TooMushArgs("Grep");
}

void WorkerGrep::execute(std::vector<std::string>& text) {
	std::vector<std::string> new_text;
	std::regex reg(word);
	std::smatch suit_str;
	for (auto const& str : text)
		if (std::regex_search(str, suit_str, reg))
			new_text.push_back(str);
	text.clear();
	text = new_text;
}

WorkerSort::WorkerSort(const std::string& argv) {
	input = true;
	output = true;
}

void WorkerSort::execute(std::vector<std::string>& text) {
	std::sort(text.begin(), text.end());
}

WorkerReplace::WorkerReplace(const std::string& argv) {
	input = true;
	output = true;
	int i = 0;
	word1.clear();
	for (; argv[i] == ' ' && i < argv.size(); i++);
	if (i == argv.size())
		throw TooFewArgs("Replace");
	for (; argv[i] != ' ' && i < argv.size(); i++)
		word1 += argv[i];
	word2.clear();
	for (; argv[i] == ' ' && i < argv.size(); i++);
	if (i == argv.size())
		throw TooFewArgs("Replace");
	for (; argv[i] != ' ' && i < argv.size(); i++)
		word2 += argv[i];
	if (i < argv.size())
		throw TooMushArgs("Replace");
}

void WorkerReplace::execute(std::vector<std::string>& text) {
	std::vector<std::string> new_text;
	std::regex reg(word1);
	for (auto& str : text)
		new_text.push_back(std::regex_replace(str, reg, word2));
	text.clear();
	text = new_text;
}

WorkerDump::WorkerDump(const std::string& argv) {
	input = true;
	output = true;
	int i = 0;
	filename.clear();
	for (; argv[i] == ' ' && i < argv.size(); i++);
	if (i == argv.size())
		throw TooFewArgs("Dump");
	for (; argv[i] != ' ' && i < argv.size(); i++)
		filename += argv[i];
	if (i < argv.size())
		throw TooMushArgs("Dump");
}

void WorkerDump::execute(std::vector<std::string>& text) {
	std::ofstream out(filename);
	for (auto& str : text)
		out << str << '\n';
	out.close();
}
