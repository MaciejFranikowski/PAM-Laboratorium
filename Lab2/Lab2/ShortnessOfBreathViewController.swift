//
//  ShortnessOfBreathViewController.swift
//  Lab2
//
//  Created by Maciej Franikowski on 16/01/2023.
//

import UIKit

class ShortnessOfBreathViewController: UIViewController {

    var probability : Int = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    // MARK: - Navigation
     override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
          if segue.identifier == "yesSegue"
          {
              if let destinationVC = segue.destination as? ResultViewController {
                  destinationVC.probability = self.probability + 20
              }
          }
          
          if segue.identifier == "noSegue"
          {
              if let destinationVC = segue.destination as? ResultViewController {
                  destinationVC.probability = self.probability - 5
              }
          }
      }

}
