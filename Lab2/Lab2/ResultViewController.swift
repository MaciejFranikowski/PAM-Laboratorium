//
//  ResultViewController.swift
//  Lab2
//
//  Created by Maciej Franikowski on 16/01/2023.
//

import UIKit

class ResultViewController: UIViewController {

    
    @IBOutlet weak var probabilityResultLabel: UILabel!
    var probability : Int = 0
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        if(probability < 0){
            probability = 0
        }
        probabilityResultLabel.text = "The probability of having Covid-19 is calculated at \(probability)%."
       }
}
