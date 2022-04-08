#include <iostream>
#include <vector>
#include <string>

#include "game.h"
#include "exceptions.h"
#include "cxxopts.hpp"

int main(int argc, char** argv){
    std::cin.sync();
    cxxopts::Options options("SeaButtle");
    options.add_options()
            ("h,help", "Get some help about program")
            ("f,first", "First player type [console|random|optimal]", cxxopts::value<std::string>()->default_value("random"))
            ("s,second", "Second player type [console|random|optimal]", cxxopts::value<std::string>()->default_value("random"))
            ("c,count", "Number of rounds", cxxopts::value<int>()->default_value("1"));
    auto opts = options.parse(argc, argv);
    if (opts.count("help")){
        std::cout << options.help() << "\n";
        return 0;
    }
    auto n = opts["c"].as<int>();
    auto typeFirst = opts["f"].as<std::string>();
    auto typeSecond = opts["s"].as<std::string>();
    try {
        proverka(n, typeFirst, typeSecond);
        std::vector<int> stat = {0, 0};
        for (int i = 0; i < n; i++) {
            auto* game = new ConsoleView(typeFirst, typeSecond);
            int g = 0;
            while (!g)
                g = game->gaming();
            stat[g - 1]++;
        }
        std::cout << stat[0] << " : " << stat[1] << "\n";
    }
    catch (std::exception& exp) {
        std::cerr << exp.what();
    }
    return 0;
}