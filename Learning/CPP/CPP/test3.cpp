#pragma once
#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>
using namespace std;

int main() {
	int n, m;
	long l;
	cin >> n >> m;
	int lowest = n*n >= m ? n*n : -1;
	if (lowest != -1) {
		for (int i = n + 1; i--; i > 0) {
			for (int j = n + 1; j--; j > 0) {
				l = i * j;
				if (l >= m and l < lowest) {
					if (l == m) {
						cout << l;
						return 0;
					}
					lowest = l;
				}
				else if (l < m)
				{
					break;
				}
			}
		}

	}
	cout << lowest;
	return 0;
}