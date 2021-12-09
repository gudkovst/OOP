#include "factory.h"


Worker* WorkerReadFactory::createWorker(std::string argv) {
	return new WorkerRead(argv);
}

Worker* WorkerWriteFactory::createWorker(std::string argv) {
	return new WorkerWrite(argv);
}

Worker* WorkerGrepFactory::createWorker(std::string argv) {
	return new WorkerGrep(argv);
}

Worker* WorkerSortFactory::createWorker(std::string argv) {
	return new WorkerSort(argv);
}

Worker* WorkerReplaceFactory::createWorker(std::string argv) {
	return new WorkerReplace(argv);
}

Worker* WorkerDumpFactory::createWorker(std::string argv) {
	return new WorkerDump(argv);
}

Worker* makeWorker(WorkerFactory* factory, std::string& argv) {
	return factory->createWorker(argv);
}
