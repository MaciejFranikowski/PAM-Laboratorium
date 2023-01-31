//
//  ContentView.swift
//  labI4
//
//  Created by Maciej Franikowski on 30/01/2023.
//

import SwiftUI

struct ContentView: View {
    @State private var checkAmount = 0.0
    @State private var numberOfPeople = 2
    @State private var tipPercentage = 20
    @State private var speed = 0.5
    
    
    @FocusState private var amountIsFocused: Bool
    
    let tipPercentages = [10, 15, 20, 25, 0]
    
    var body: some View {
        NavigationView {
            Form {
                Section {
                    TextField("Amount", value: $checkAmount, format: .currency(code: Locale.current.currencyCode ?? "USD"))
                        .keyboardType(.decimalPad).focused($amountIsFocused)
                    
                }header: {
                    Text("Your total bill value")
                }
                Section {
                    Picker("Number of people", selection: $numberOfPeople) {
                        ForEach(2 ..< 100) {
                            Text("\($0) people")
                        }
                    }
                }
                Section {
                    Picker("Tip percentage", selection: $tipPercentage) {
                        ForEach(tipPercentages, id: \.self) {
                            Text($0, format: .percent)
                        }
                    }.pickerStyle(.segmented)
                } header: {
                    Text("How much tip do you want to leave?")
                }
                Section {
                    Text(totalPerPerson, format: .currency(code: Locale.current.currencyCode ?? "USD"))
                } header: {
                    Text("Your bill amount")
                }
                Section {
                    Text(totalAmout, format: .currency(code: Locale.current.currencyCode ?? "USD"))
                } header: {
                    Text("Your bill total per person")
                }
                Section {
                    Slider(
                        value: $speed,
                        in: 0...1
                    )
                    EllipticalGradient(colors:[Color.purple, Color.yellow], center: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/, startRadiusFraction: /*@START_MENU_TOKEN@*/0.0/*@END_MENU_TOKEN@*/, endRadiusFraction: speed)
                }
                
            }.navigationTitle("WeSplit - Franikowski").toolbar {
                ToolbarItemGroup(placement: .keyboard) {
                    Spacer()
                    
                    Button("Done") {
                        amountIsFocused = false
                    }
                }
            }
        }
    }
    
    var totalPerPerson: Double {
        let peopleCount = Double(numberOfPeople + 2)
        let tipSelection = Double(tipPercentage)
        
        let tipValue = checkAmount / 100 * tipSelection
        let grandTotal = checkAmount + tipValue
        let amountPerPerson = grandTotal / peopleCount
        
        return amountPerPerson
    }
    var totalAmout: Double {
        return totalPerPerson * Double(numberOfPeople + 2)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
