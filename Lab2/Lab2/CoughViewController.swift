//
//  CoughViewController.swift
//  Lab2
//
//  Created by Maciej Franikowski on 16/01/2023.
//

import UIKit

class CoughViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
    }
    // MARK: - Navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "yesSegue"
        {
            if let destinationVC = segue.destination as? FeverViewController {
                destinationVC.probability = 35
            }
        }
        
        if segue.identifier == "noSegue"
        {
            if let destinationVC = segue.destination as? FeverViewController {
                destinationVC.probability = 0
            }
        }
    }
    

}
