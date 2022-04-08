//
// Created by User on 16.01.2022.
//
#include "gamer.h"

////Gamer

int Gamer::getCountShips() const{
    return CountShips;
}

bool Gamer::putShip(int shiplen, int x, int y, int orient){
    if (!shiplen){
        if (x < 10 && y < 10)
            MyField[x][y] = aroundShip;
        return true;
    }
    if (x >= 10 || y >= 10 || x < 0 || y < 0) return false;
    if (MyField[x][y] != empty) return false;
    int x1 = (orient)? x : x + 1;
    int y1 = (orient)? y + 1 : y;
    if (putShip(shiplen - 1, x1, y1, orient)){
        MyField[x][y] = ship;
        if (orient){///horiz
            if (x > 0) {
                MyField[x-1][y] = aroundShip;
                if (y < 9) MyField[x-1][y+1] = aroundShip;
            }
            if (x < 9) {
                MyField[x+1][y] = aroundShip;
                if (y < 9) MyField[x+1][y+1] = aroundShip;
            }
            if (y > 0) {
                MyField[x][y-1] = aroundShip;
                if (x > 0) MyField[x-1][y-1] = aroundShip;
                if (x < 9) MyField[x+1][y-1] = aroundShip;
            }
        }
        else{///vert
            if (y > 0) {
                MyField[x][y-1] = aroundShip;
                if (x < 9) MyField[x+1][y-1] = aroundShip;
            }
            if (y < 9) {
                MyField[x][y+1] = aroundShip;
                if (x < 9) MyField[x+1][y+1] = aroundShip;
            }
            if (x > 0) {
                MyField[x-1][y] = aroundShip;
                if (y > 0) MyField[x-1][y-1] = aroundShip;
                if (y < 9 && x < 9) MyField[x+1][y-1] = aroundShip;
            }
        }
        return true;
    }
    else return false;
}

void Gamer::appointEnemy(Gamer* protiv){
    enemy = protiv;
}

void Gamer::buryShip(){
    CountShips--;
}

Mark Gamer::resShot(int x, int y){
    if (MyField[x][y] == aroundShip || MyField[x][y] == empty || MyField[x][y] == hit)
        return miss;
    MyField[x][y] = hit;
    for (int i = x; i >= 0 && MyField[i][y] != aroundShip; i--)
        if (MyField[i][y] == ship)
            return hit;
    for (int i = x; i < 10 && MyField[i][y] != aroundShip; i++)
        if (MyField[i][y] == ship)
            return hit;
    for (int i = y; i >= 0 && MyField[x][i] != aroundShip; i--)
        if (MyField[x][i] == ship)
            return hit;
    for (int i = y; i < 10 && MyField[x][i] != aroundShip; i++)
        if (MyField[x][i] == ship)
            return hit;
    buryShip();
    return getCountShips()? kill : end;
}

void Gamer::makeBorder(int x, int y){
    EnemyField[x][y] = kill;
    if (x > 0){
        if (EnemyField[x-1][y] == hit){
            if (y > 0) EnemyField[x][y-1] = miss;
            if (y < 9) EnemyField[x][y+1] = miss;
            makeBorder(x-1, y);
        }
        else if (EnemyField[x-1][y] != kill){
            EnemyField[x-1][y] = miss;
            if (y > 0) EnemyField[x-1][y-1] = miss;
            if (y < 9) EnemyField[x-1][y+1] = miss;
        }
    }
    if (x < 9){
        if (EnemyField[x+1][y] == hit){
            if (y > 0) EnemyField[x][y-1] = miss;
            if (y < 9) EnemyField[x][y+1] = miss;
            makeBorder(x+1, y);
        }
        else if (EnemyField[x+1][y] != kill){
            EnemyField[x+1][y] = miss;
            if (y > 0) EnemyField[x+1][y-1] = miss;
            if (y < 9) EnemyField[x+1][y+1] = miss;
        }
    }
    if (y > 0){
        if (EnemyField[x][y-1] == hit){
            if (x > 0) EnemyField[x-1][y] = miss;
            if (x < 9) EnemyField[x+1][y] = miss;
            makeBorder(x, y-1);
        }
        else if (EnemyField[x][y-1] != kill){
            EnemyField[x][y-1] = miss;
            if (x > 0) EnemyField[x-1][y-1] = miss;
            if (x < 9) EnemyField[x+1][y-1] = miss;
        }
    }
    if (y < 9){
        if (EnemyField[x][y+1] == hit){
            if (x > 0) EnemyField[x-1][y] = miss;
            if (x < 9) EnemyField[x+1][y] = miss;
            makeBorder(x, y+1);
        }
        else if (EnemyField[x][y+1] != kill){
            EnemyField[x][y+1] = miss;
            if (x > 0) EnemyField[x-1][y+1] = miss;
            if (x < 9) EnemyField[x+1][y+1] = miss;
        }
    }
}

void Gamer::print(Mark c){
    if (c == ship) std::cout << "O";
    else if (c == aroundShip || c == empty)
        std::cout << " ";
    else if (c == miss) std::cout << "*";
    else std::cout << "X";
}

void Gamer::lookField(bool regime){
    for (int i = 0; i < 10; i++) std::cout << "_";
    std::cout << "\n";
    for (int x = 0; x < 10; x++){
        std::cout << "|";
        for (int y = 0; y < 10; y++) {
            if (!regime)
                print(MyField[x][y]);
            else print(EnemyField[x][y]);
        }
        std::cout << "|\n";
    }
    for (int i = 0; i < 10; i++) std::cout << "-";
    std::cout << "\n";
}

//////ConsoleGamer

ConsoleGamer::ConsoleGamer(){
    CountShips = 10;
}

ConsoleGamer::ConsoleGamer(Gamer* protiv){
    CountShips = 10;
    enemy = protiv;
    protiv->appointEnemy(this);
}

void ConsoleGamer::setShips(){
    for (int shiplen = 4; shiplen > 0; shiplen--)
        for (int shipcount = 0; shipcount < 5 - shiplen; shipcount++){
            bool success = false;
            while (!success){
                lookField(false);
                int x, y, orient;
                std::cout << "enter the top left coordinate of the ship size " << shiplen << " ";
                std::cin >> x >> y;
                std::cout << "enter the orientation of this ship ";// horiz - 1, vert - 0
                std::cin >> orient;
                success = putShip(shiplen, x-1, y-1, orient);
                if (!success)
                    std::cout << "incorrect data\n";
            }
        }
}

Mark ConsoleGamer::shot(){
    std::cout << "Your field\n";
    lookField(false);
    std::cout<<"\nEnemy field\n";
    lookField(true);
    bool correct = false;
    int x, y;
    while (!correct){
        std::cout << "enter the coordinates for the shot ";
        std::cin >> x >> y;
        x--;
        y--;
        correct = (x < 10 && y < 10 && x >= 0 && y >= 0);
        if (!correct)
            std::cout << "incorrect data\n";
    }
    Mark res = enemy->resShot(x, y);
    if (res == miss)
        EnemyField[x][y] = miss;
    else EnemyField[x][y] = hit;
    if (res == kill)
        makeBorder(x, y);
    return res;
}

/////ComputerGamer

std::vector<std::pair<int, int>> ComputerGamer::emptyCells(){
    std::vector<std::pair<int, int>> res;
    for (int x = 0; x < 10; x++)
        for (int y = 0; y < 10; y++)
            if (EnemyField[x][y] == empty)
                res.push_back({x, y});
    return res;
}

std::vector<std::pair<int, int>> ComputerGamer::freeCells(int shiplen, int orient){
    std::vector<std::pair<int, int>> res;
    if (orient) //vert
        for (int x = 0; x < 10; x++)
            for (int y = 0; y <= 10 - shiplen; y++){
                int i;
                for (i = 0; i < shiplen && MyField[x][y+i] == empty; i++);
                if (i == shiplen) res.push_back({x, y});
            }
    else //horiz
        for (int x = 0; x <= 10 - shiplen; x++)
            for (int y = 0; y < 10; y++){
                int i;
                for (i = 0; i < shiplen && MyField[x+i][y] == empty; i++);
                if (i == shiplen) res.push_back({x, y});
            }
    return res;
}

std::vector<std::pair<int, int>> ComputerGamer::environment(std::pair<int, int> koord){
    std::vector<std::pair<int, int>> res;
    int x = koord.first, y = koord.second;
    if (x > 0){
        if (y > 0 && EnemyField[x-1][y-1] == empty)
            res.push_back({x-1, y-1});
        if (EnemyField[x-1][y] == empty)
            res.push_back({x-1, y});
        if (y < 9 && EnemyField[x-1][y+1] == empty)
            res.push_back({x-1, y+1});
    }
    if (y > 0 && EnemyField[x][y-1] == empty)
        res.push_back({x, y-1});
    if (y < 9 && EnemyField[x][y+1] == empty)
        res.push_back({x, y+1});
    if (x < 9){
        if (y > 0 && EnemyField[x+1][y-1] == empty)
            res.push_back({x+1, y-1});
        if (EnemyField[x+1][y] == empty)
            res.push_back({x+1, y});
        if (y < 9 && EnemyField[x+1][y+1] == empty)
            res.push_back({x+1, y+1});
    }
    return res;
}

std::pair<Mark, std::pair<int, int>> ComputerGamer::killShip(std::pair<int, int> wound){
    auto koords = environment(wound);
    if (koords.empty())
        koords = emptyCells();
    if (koords.empty()) return {end, {0,0}};
    auto koord = koords[std::rand() % koords.size()];
    Mark res = enemy->resShot(koord.first, koord.second);
    return {res, koord};
}

////RandomGamer

RandomGamer::RandomGamer(){
    CountShips = 10;
    woundedShip = false;
}

RandomGamer::RandomGamer(Gamer* protiv){
    CountShips = 10;
    woundedShip = false;
    enemy = protiv;
    protiv->appointEnemy(this);
}

void RandomGamer::setShips(){
    for (int shiplen = 4; shiplen > 0; shiplen--)
        for (int shipcount = 0; shipcount < 5 - shiplen; shipcount++){
            bool success = false;
            while (!success){
                int orient = std::rand() % 2;
                auto koords = freeCells(shiplen, orient);
                auto koord = koords[std::rand() % koords.size()];
                success = putShip(shiplen, koord.first, koord.second, orient);
            }
        }
}

Mark RandomGamer::shot(){
    if (woundedShip){
        auto res = killShip(lastShot);
        if (res.first == miss)
            EnemyField[res.second.first][res.second.second] = miss;
        else EnemyField[res.second.first][res.second.second] = hit;
        if (res.first == hit)
            lastShot = res.second;
        if (res.first == kill){
            woundedShip = false;
            makeBorder(lastShot.first, lastShot.second);
        }
        return res.first;
    }
    auto koords = emptyCells();
    if (koords.empty()) return end;
    auto koord = koords[std::rand() % koords.size()];
    int x = koord.first, y = koord.second;
    Mark res = enemy->resShot(x, y);
    if (res == miss)
        EnemyField[x][y] = miss;
    else {
        EnemyField[x][y] = hit;
        lastShot = {x, y};
    }
    if (res == hit)
        woundedShip = true;
    if (res == kill)
        makeBorder(x, y);
    return res;
}


//////OptimalGamer

OptimalGamer::OptimalGamer(){
    CountShips = 10;
    woundedShip = false;
    lastShot = {std::rand() % 10, std::rand() % 10};
}

OptimalGamer::OptimalGamer(Gamer* protiv){
    CountShips = 10;
    woundedShip = false;
    lastShot = {std::rand() % 10, std::rand() % 10};
    enemy = protiv;
    protiv->appointEnemy(this);
}

void OptimalGamer::setShips(){
    for (int shiplen = 4; shiplen > 2; shiplen--)
        for (int shipcount = 5 - shiplen; shipcount > 0; shipcount--){
            bool success = false;
            while (!success){
                int orient = std::rand() % 2, x, y;
                if (orient){
                    x = std::rand() % (11 - shiplen);
                    int q = std::rand() % 2;
                    y = std::rand() % 3 + 7 * q;
                }
                else{
                    y = std::rand() % (11 - shiplen);
                    int q = std::rand() % 2;
                    x = std::rand() % 3 + 7 * q;
                }
                success = putShip(shiplen, x, y, orient);
            }
        }
    for (int shiplen = 2; shiplen > 0; shiplen--)
        for (int shipcount = 0; shipcount < 5 - shiplen; shipcount++){
            bool success = false;
            while (!success){
                int orient = std::rand() % 2;
                auto koords = freeCells(shiplen, orient);
                auto koord = koords[std::rand() % koords.size()];
                success = putShip(shiplen, koord.first, koord.second, orient);
            }
        }
}

Mark OptimalGamer::shot(){
    if (woundedShip){
        auto res = killShip(lastShot);
        if (res.first == miss)
            EnemyField[res.second.first][res.second.second] = miss;
        else EnemyField[res.second.first][res.second.second] = hit;
        if (res.first == hit)
            lastShot = res.second;
        if (res.first == kill){
            woundedShip = false;
            makeBorder(lastShot.first, lastShot.second);
            auto koords = emptyCells();
            if (koords.empty()) koords.push_back({0,0});
            lastShot = koords[std::rand() % koords.size()];
        }
        return res.first;
    }
    auto koords = environment(lastShot);
    if (koords.empty())
        koords = emptyCells();
    if (koords.empty()) return end;
    auto koord = koords[std::rand() % koords.size()];
    int x = koord.first, y = koord.second;
    lastShot = {x, y};
    Mark res = enemy->resShot(x, y);
    if (res == miss)
        EnemyField[x][y] = miss;
    else
        EnemyField[x][y] = hit;
    if (res == hit)
        woundedShip = true;
    if (res == kill){
        makeBorder(x, y);
        auto koords = emptyCells();
        if (koords.empty()) koords.push_back({0, 0});
        lastShot = koords[std::rand() % koords.size()];
    }
    return res;
}
