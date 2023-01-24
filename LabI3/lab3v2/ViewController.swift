//
//  ViewController.swift
//  lab3v2
//
//  Created by Maciej Franikowski on 23/01/2023.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var numberOfDrinksTextField: UITextField!
    @IBOutlet weak var tableNumberTextField: UITextField!
    @IBOutlet weak var drinkTypeSegmentedControl: UISegmentedControl!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "finalizeOrder"
        {
            if let destinationVC = segue.destination as? OrderResultViewController {
                if let tableNumber = Int(tableNumberTextField.text ?? "0"){
                    destinationVC.tableNumber = tableNumber
                }
                if let numberOfDrinks = Int(numberOfDrinksTextField.text ?? "0"){
                    destinationVC.numberOfDrinks = numberOfDrinks
                }
                let drinkName = drinkTypeSegmentedControl.titleForSegment(at: drinkTypeSegmentedControl.selectedSegmentIndex) ?? "coffee"
                destinationVC.drinkName = drinkName
            }
        }
    }
    
}

