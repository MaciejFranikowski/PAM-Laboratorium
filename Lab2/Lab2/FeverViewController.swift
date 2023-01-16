//
//  FeverViewController.swift
//  Lab2
//
//  Created by Maciej Franikowski on 16/01/2023.
//

import UIKit

class FeverViewController: UIViewController {

    
    var probability : Int = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        print(probability)
    }
    
    // MARK: - Navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
         if segue.identifier == "yesSegue"
         {
             if let destinationVC = segue.destination as? ShortnessOfBreathViewController {
                 destinationVC.probability = self.probability + 25
             }
         }
         
         if segue.identifier == "noSegue"
         {
             if let destinationVC = segue.destination as? ShortnessOfBreathViewController {
                 destinationVC.probability = self.probability - 10
             }
         }
     }

}
