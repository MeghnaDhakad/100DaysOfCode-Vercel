#include <stdio.h>

int main() {
    // Allocate a large enough buffer for the string. 
    // 100005 is a standard safe size for competitive programming inputs.
    char s[100005];
    
    // Read the string from standard input
    if (scanf("%100004s", s) != 1) {
        return 0;
    }

    // Hash array to keep track of seen characters.
    // Size 26 for lowercase English letters ('a' to 'z').
    // initialized to 0 (false).
    int seen[26] = {0}; 
    
    int found = 0;

    // Iterate through the string character by character
    for (int i = 0; s[i] != '\0'; i++) {
        int index = s[i] - 'a'; // Convert char to an index 0-25
        
        // If we have already seen this character, it's our answer
        if (seen[index]) {
            printf("%c\n", s[i]);
            found = 1;
            break;
        } 
        // Otherwise, mark it as seen and continue
        else {
            seen[index] = 1;
        }
    }

    // If the loop finishes and no character was repeated
    if (!found) {
        printf("-1\n");
    }

    return 0;
}