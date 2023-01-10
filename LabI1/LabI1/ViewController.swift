//
//  ViewController.swift
//  LabI1
//
//  Created by Maciej Franikowski on 09/01/2023.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var billResultLabel: UILabel!
    @IBOutlet weak var tipResultLabel: UILabel!
    @IBOutlet weak var foodMarkSegmentedControl: UISegmentedControl!
    @IBOutlet weak var serviceMarkSegmentedControl: UISegmentedControl!
    @IBOutlet weak var maxTipTextField: UITextField!
    @IBOutlet weak var billValueTextField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    @IBAction func calculateTip(_ sender: Any) {
        if let bill = Double(billValueTextField.text ?? "0"), let tip = Double(maxTipTextField.text ?? "0") {
            let maxServiceMark = Double(serviceMarkSegmentedControl.numberOfSegments)
            let maxFoodMark = Double(foodMarkSegmentedControl.numberOfSegments)
            let serviceMark = Double((serviceMarkSegmentedControl.selectedSegmentIndex + 1)) / maxServiceMark
            let foodMark = Double((foodMarkSegmentedControl.selectedSegmentIndex + 1)) / maxFoodMark
            let finalMark = (serviceMark + foodMark) / 2
            let finalTip = ((tip * Double(finalMark)) / 100) * bill
            let finalTipRounded = Double(round(100 * finalTip) / 100)
            let finalBill = bill + finalTipRounded
            tipResultLabel.text = "$\(finalTipRounded)"
            billResultLabel.text = "$\(finalBill)"
        }
    }
}























