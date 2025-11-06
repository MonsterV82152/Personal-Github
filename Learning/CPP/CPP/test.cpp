#include "test.h"

int main2() {
    int length;
    cin >> length;
    vector<int> lis(length);
    for (int i = 0; i < length; i++) {
        cin >> lis[i];
    }

    double largest_sum = 0;
	int previous_sum = accumulate(lis.begin(), lis.end(), 0);
    int min_val = *min_element(lis.begin(), lis.end());
    vector<int> indices;

    for (int i = 1; i < length - 1; i++) {
        vector<int> sub(lis.begin() + i, lis.end());
        if (min_val == lis[i - 1]) {
            min_val = *min_element(sub.begin(), sub.end());
        }

		int sub_sum = previous_sum -= lis[i-1];
        double suma = (double)(sub_sum - min_val) / (sub.size() - 1);
        
        if (suma > largest_sum) {
            largest_sum = suma;
            indices.clear();
            indices.push_back(i);
        } else if (suma == largest_sum) {
            indices.push_back(i);
        }
    }

    for (int idx : indices) {
        cout << idx << endl;
    }
    return 0;
}

