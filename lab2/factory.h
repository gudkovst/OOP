#pragma once

#include <iostream>
#include <string>
#include "worker.h"

class WorkerFactory{
	public:
		virtual Worker* createWorker(std::string argv) = 0;
};

class WorkerReadFactory: public WorkerFactory{
	public:
		Worker* createWorker(std::string argv) override;
};

class WorkerWriteFactory: public WorkerFactory{
	public:
		Worker* createWorker(std::string argv) override;
};

class WorkerGrepFactory: public WorkerFactory{
	public:
		Worker* createWorker(std::string argv) override;
};

class WorkerSortFactory: public WorkerFactory{
	public:
		Worker* createWorker(std::string argv) override;
};

class WorkerReplaceFactory: public WorkerFactory{
	public:
		Worker* createWorker(std::string argv) override;
};

class WorkerDumpFactory: public WorkerFactory{
	public:
		Worker* createWorker(std::string argv) override;
};

Worker* makeWorker(WorkerFactory* factory, std::string& argv);
