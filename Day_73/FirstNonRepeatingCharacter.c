#include <stdio.h>

int main() {
    // Allocate a buffer large enough for the string.
    // 100005 is a standard safe size for string inputs in competitive programming.
    char s[100005];
    
    // Read the string from standard input
    if (scanf("%100004s", s) != 1) {
        return 0;
    }

    // Frequency array to keep track of character counts.
    // Size 26 for lowercase English letters ('a' to 'z').
    // initialized to 0.
    int freq[26] = {0}; 
    
    // First pass: Count the frequency of each character
    for (int i = 0; s[i] != '\0'; i++) {
        int index = s[i] - 'a'; // Convert char to an index 0-25
        freq[index]++;
    }

    int found = 0;

    // Second pass: Find the first character with a frequency of 1
    for (int i = 0; s[i] != '\0'; i++) {
        int index = s[i] - 'a';
        
        if (freq[index] == 1) {
            printf("%c\n", s[i]);
            found = 1;
            break;
        } 
    }

    // If the loop finishes and all characters repeat
    if (!found) {
        printf("$\n");
    }

    return 0;
}