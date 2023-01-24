//
//  OrderResultViewController.swift
//  lab3v2
//
//  Created by Maciej Franikowski on 24/01/2023.
//

import UIKit

class OrderResultViewController: UIViewController {

    @IBOutlet weak var orderResultLabel: UILabel!
    var numberOfDrinks : Int = 0
    var tableNumber : Int = 0
    var drinkName : String = ""
    var pluralDrinks : String = ""
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        if numberOfDrinks > 1{
            pluralDrinks = "s"
        }
        orderResultLabel.text = "You ordered \(numberOfDrinks) \(drinkName)\(pluralDrinks) to the table \(tableNumber)."
   }

}
