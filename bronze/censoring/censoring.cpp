#include <algorithm>
#include <cstdio>
#include <iostream>
#include <vector>

using namespace std;

int main(){
    freopen("censor.in", "r", stdin);

    string S;
    cin >> S;
    
    string T;
    cin >> T;


    string out = "";

    for(int i = 0; i< S.size(); ){

        if(out.size() >= T.size() &&  out.substr(out.size()-T.size()) == T){
            out.resize(out.size()-T.size());
        } else{
            out += S[i];
            i++;
        }

    }

    freopen("censor.out", "w", stdout);
    cout << out;
}