#pragma once

#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <map>

#include "factory.h"
#include "worker.h"
#include "exceptions.h"

enum ParserState {
    dream, open, close, end
};

class Parser {
private:
    std::ifstream fin;
    std::string buffer;
    std::vector<int> commandSequence;
    std::map<int, Worker*> blocks;
    WorkerReadFactory* ReadFactory;
    WorkerWriteFactory* WriteFactory;
    WorkerGrepFactory* GrepFactory;
    WorkerSortFactory* SortFactory;
    WorkerReplaceFactory* ReplaceFactory;
    WorkerDumpFactory* DumpFactory;

    std::vector<std::string> parseBlock(const std::string& buffer);
    std::vector<int> parseSequence(const std::string& buffer);

public:
    Parser(const std::string& filename);
    void parse();

    std::vector<int> getSequence();
    std::map<int, Worker*> getBlocks();
};
