#pragma once
#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>
using namespace std;

static void printVec(vector<int> a) {
	for (int i : a) {
		cout << i << endl;
	}
}
int main3() {
	int n, m;
	long k;
	cin >> n >> m >> k;
	vector<pair<int, int>> lis(m);
	for (int i = 0; i < m; i++) {
		cin >> lis[i].first >> lis[i].second;
	}
	vector<vector<int>> results;
	vector<int> indices(n);
	iota(indices.begin(), indices.end(), 1);
	results.push_back(indices);

	for (long i = 0; i < k; i++) {
		for (pair<int, int> j : lis) {
			reverse(indices.begin() + j.first - 1, indices.begin() + j.second);
		}
		if (results[0] == indices) {
			cout << "Error Here?" << endl;
			printVec(results[k % (i + 1)]);
			return 0;
		}
		results.push_back(indices);
	}
	printVec(indices);
	return 0;
}