// Airgead Banking App - Enhanced Step 3 Implementation
// Updated for CS 499 Milestone Two Software Design and Engineering

#include <iostream>
#include <iomanip>
#include <limits>
#include <vector>

using namespace std;

// Struct to store yearly investment results
struct YearlyResult {
    int year;
    double yearEndBalance;
    double interestEarned;
};

// Function to print yearly investment details
void printDetails(const vector<YearlyResult>& results) {
    for (const auto& result : results) {
        cout << result.year << "\t\t$"
             << fixed << setprecision(2) << result.yearEndBalance
             << "\t\t$" << result.interestEarned << endl;
    }
}

// Investment class encapsulating data and behavior
class Investment {
private:
    double initialInvestment;
    double monthlyDeposit;
    double annualInterest;
    int numberOfYears;

    // Generic input validation helper
    template <typename T>
    void getValidatedInput(const string& prompt, T& value, T minValue) {
        while (true) {
            cout << prompt;
            cin >> value;

            if (cin.fail() || value < minValue) {
                cout << "Invalid input. Please enter a value greater than or equal to "
                     << minValue << ".\n";
                cin.clear();
                cin.ignore(numeric_limits<streamsize>::max(), '\n');
            } else {
                break;
            }
        }
    }

    // Core calculation logic reused by both scenarios
    vector<YearlyResult> calculateInvestment(bool includeMonthlyDeposit) {
        vector<YearlyResult> results;
        double balance = initialInvestment;
        double monthlyRate = (annualInterest / 100.0) / 12.0;

        for (int year = 1; year <= numberOfYears; ++year) {
            double interestEarnedThisYear = 0.0;

            for (int month = 1; month <= 12; ++month) {
                double interest = balance * monthlyRate;
                balance += interest;
                interestEarnedThisYear += interest;

                if (includeMonthlyDeposit) {
                    balance += monthlyDeposit;
                }
            }

            results.push_back({year, balance, interestEarnedThisYear});
        }

        return results;
    }

public:
    // Collect validated user input
    void getUserInput() {
        cout << "***********************************" << endl;
        cout << "********** Data Input *************" << endl;

        getValidatedInput("Initial Investment Amount: $", initialInvestment, 0.01);
        getValidatedInput("Monthly Deposit: $", monthlyDeposit, 0.00);
        getValidatedInput("Annual Interest (%): ", annualInterest, 0.01);
        getValidatedInput("Number of years: ", numberOfYears, 1);

        cout << "\nPress any key to continue...";
        cin.ignore(numeric_limits<streamsize>::max(), '\n');
        cin.get();
    }

    // Display results without monthly deposits
    void displayWithoutMonthlyDeposits() {
        cout << "\n   Balance and Interest Without Additional Monthly Deposits" << endl;
        cout << "============================================================" << endl;
        cout << "Year\t\tYear End Balance\t\tInterest Earned" << endl;
        cout << "------------------------------------------------------------" << endl;

        vector<YearlyResult> results = calculateInvestment(false);
        printDetails(results);
    }

    // Display results with monthly deposits
    void displayWithMonthlyDeposits() {
        cout << "\n   Balance and Interest With Additional Monthly Deposits" << endl;
        cout << "==========================================================" << endl;
        cout << "Year\t\tYear End Balance\t\tInterest Earned" << endl;
        cout << "----------------------------------------------------------" << endl;

        vector<YearlyResult> results = calculateInvestment(true);
        printDetails(results);
    }
};

// Main program loop
int main() {
    char runAgain;

    do {
        Investment app;
        app.getUserInput();
        app.displayWithoutMonthlyDeposits();
        app.displayWithMonthlyDeposits();

        cout << "\nWould you like to run another calculation? (Y/N): ";
        cin >> runAgain;
        cin.ignore(numeric_limits<streamsize>::max(), '\n');

    } while (runAgain == 'Y' || runAgain == 'y');

    cout << "\nThank you for using the Airgead Banking App!" << endl;
    return 0;
}
