//
//  ViewController.swift
//  lab1_cg
//
//  Created by Roma on 3/25/21.
//

import UIKit

class ViewController: UIViewController {

    
    @IBOutlet weak var ColorPicker: UIButton!
    
    @IBOutlet weak var ColorViewFinal: UIView!
    
    @IBOutlet weak var RgbLabel: UILabel!
    @IBOutlet weak var CmykLabel: UILabel!
    @IBOutlet weak var HsvLabel: UILabel!
    
    @IBOutlet weak var RgbSliderRed: UISlider!
    @IBOutlet weak var RgbSliderGreen: UISlider!
    @IBOutlet weak var RgbSliderBlue: UISlider!
    
    @IBOutlet weak var CmykSliderC: UISlider!
    @IBOutlet weak var CmykSliderM: UISlider!
    @IBOutlet weak var CmykSliderY: UISlider!
    @IBOutlet weak var CmykSliderK: UISlider!
    
    
    @IBOutlet weak var HsvSliderH: UISlider!
    @IBOutlet weak var HsvSliderS: UISlider!
    @IBOutlet weak var HsvSliderV: UISlider!
    
    
    
    
    @IBOutlet weak var RgbTextFieldRed: UITextField!
    @IBOutlet weak var RgbTextFieldGreen: UITextField!
    @IBOutlet weak var RgbTextFieldBlue: UITextField!
    
    @IBOutlet weak var CmykTextFieldC: UITextField!
    @IBOutlet weak var CmykTextFieldM: UITextField!
    @IBOutlet weak var CmykTextFieldY: UITextField!
    @IBOutlet weak var CmykTextFieldK: UITextField!
    
    
    @IBOutlet weak var CmykTextFieldH: UITextField!
    @IBOutlet weak var CmykTextFieldS: UITextField!
    @IBOutlet weak var CmykTextFieldV: UITextField!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        //270
        
       
        
        
        RgbTextFieldRed.text = "255"
        RgbTextFieldGreen.text = "255"
        RgbTextFieldBlue.text = "255"
        
        RgbSliderRed.value = 1.0
        RgbSliderGreen.value = 1.0
        RgbSliderBlue.value = 1.0
        
        CmykTextFieldC.text = "0"
        CmykTextFieldM.text = "0"
        CmykTextFieldY.text = "0"
        CmykTextFieldK.text = "0"
        
        CmykSliderC.value = 0.0
        CmykSliderM.value = 0.0
        CmykSliderY.value = 0.0
        CmykSliderK.value = 0.0
        
        CmykTextFieldH.text = "0"
        CmykTextFieldS.text = "0"
        CmykTextFieldV.text = "100"
        
        HsvSliderH.value = 0.0
        HsvSliderS.value = 0.0
        HsvSliderV.value = 1.0
    }

    @IBAction func RgbSliderRedAction(_ sender: Any) {
        let color = UIColor(red: CGFloat(RgbSliderRed.value), green: CGFloat(RgbSliderGreen.value), blue: CGFloat(RgbSliderBlue.value), alpha: 1.0)
        RgbTextFieldRed.text = "\(Int(RgbSliderRed.value * 255))"
        self.view.backgroundColor = color
        UpdateCmykParams()
        UpdateHsvParams()
    }
    @IBAction func RgbSliderGreenAction(_ sender: Any) {
        let color = UIColor(red: CGFloat(RgbSliderRed.value), green: CGFloat(RgbSliderGreen.value), blue: CGFloat(RgbSliderBlue.value), alpha: 1.0)
        RgbTextFieldGreen.text = "\(Int(RgbSliderGreen.value * 255))"
        self.view.backgroundColor = color
        UpdateCmykParams()
        UpdateHsvParams()
    }
    @IBAction func RgbSliderBlueAction(_ sender: Any) {
        let color = UIColor(red: CGFloat(RgbSliderRed.value), green: CGFloat(RgbSliderGreen.value), blue: CGFloat(RgbSliderBlue.value), alpha: 1.0)
        RgbTextFieldBlue.text = "\(Int(RgbSliderBlue.value * 255))"
        self.view.backgroundColor = color
        UpdateCmykParams()
        UpdateHsvParams()
    }
    
    func UpdateCmykParams() {
        let red = self.view.backgroundColor!.red
        let green = self.view.backgroundColor!.green
        let blue = self.view.backgroundColor!.blue
        let (c, m, y, k) = RGBtoCMYK(r: red, g: green, b: blue)
        
        CmykTextFieldC.text = "\(Int(c * 100))"
        CmykTextFieldM.text = "\(Int(m * 100))"
        CmykTextFieldY.text = "\(Int(y * 100))"
        CmykTextFieldK.text = "\(Int(k * 100))"
        
        self.CmykSliderC.value = Float(c)
        self.CmykSliderM.value = Float(m)
        self.CmykSliderY.value = Float(y)
        self.CmykSliderK.value = Float(k)
    }
    
    func UpdateHsvParams() {
        let red = self.view.backgroundColor!.red
        let green = self.view.backgroundColor!.green
        let blue = self.view.backgroundColor!.blue
        let (h, s, v) = rgbToHsv(r: red, g: green, b: blue)
        
        CmykTextFieldH.text = "\(h)"
        CmykTextFieldS.text = "\(s)"
        CmykTextFieldV.text = "\(v)"
        
        self.HsvSliderH.value = (Float(h) / 100) / 3.6
        self.HsvSliderS.value = Float(s) / 100
        self.HsvSliderV.value = Float(v) / 100
    }
    
    @IBAction func HsvCmykCAction(_ sender: Any) {
        //let (r, g, b) = CMYKtoRGB(c: c, m: m, y: y, k: k)
        //.backgroundColor = UIColor(red: r, green: g, blue: b, alpha: 1.0)
        let (red, green, blue) = CMYKtoRGB(c: CGFloat(CmykSliderC.value), m: CGFloat(CmykSliderM.value), y: CGFloat(CmykSliderY.value), k: CGFloat(CmykSliderK.value))
        let color = UIColor(red: red, green: green, blue: blue, alpha: 1.0)
        CmykTextFieldC.text = "\(Int(CmykSliderC.value * 100))"
        self.view.backgroundColor = color
        UpdateRgbParams()
        UpdateHsvParams()
    }
    @IBAction func HsvCmykMAction(_ sender: Any) {
        let (red, green, blue) = CMYKtoRGB(c: CGFloat(CmykSliderC.value), m: CGFloat(CmykSliderM.value), y: CGFloat(CmykSliderY.value), k: CGFloat(CmykSliderK.value))
        let color = UIColor(red: red, green: green, blue: blue, alpha: 1.0)
        CmykTextFieldM.text = "\(Int(CmykSliderM.value * 100))"
        self.view.backgroundColor = color
        UpdateRgbParams()
        UpdateHsvParams()
    }
    @IBAction func HsvCmykYAction(_ sender: Any) {
        let (red, green, blue) = CMYKtoRGB(c: CGFloat(CmykSliderC.value), m: CGFloat(CmykSliderM.value), y: CGFloat(CmykSliderY.value), k: CGFloat(CmykSliderK.value))
        let color = UIColor(red: red, green: green, blue: blue, alpha: 1.0)
        CmykTextFieldY.text = "\(Int(CmykSliderY.value * 100))"
        self.view.backgroundColor = color
        UpdateRgbParams()
        UpdateHsvParams()
    }
    @IBAction func HsvCmykKAction(_ sender: Any) {
        let (red, green, blue) = CMYKtoRGB(c: CGFloat(CmykSliderC.value), m: CGFloat(CmykSliderM.value), y: CGFloat(CmykSliderY.value), k: CGFloat(CmykSliderK.value))
        let color = UIColor(red: red, green: green, blue: blue, alpha: 1.0)
        CmykTextFieldK.text = "\(Int(CmykSliderK.value * 100))"
        self.view.backgroundColor = color
        UpdateRgbParams()
        UpdateHsvParams()
    }
    
    
    func UpdateRgbParams() {
        let red = self.view.backgroundColor!.red
        let green = self.view.backgroundColor!.green
        let blue = self.view.backgroundColor!.blue
       
        RgbTextFieldRed.text = "\(round(red * 255))"
        RgbTextFieldGreen.text = "\(round(green * 255))"
        RgbTextFieldBlue.text = "\(round(blue * 255))"
        
        self.RgbSliderRed.value = Float(red)
        self.RgbSliderGreen.value = Float(green)
        self.RgbSliderBlue.value = Float(blue)
    }
    
    
    @IBAction func HsvSliderHAction(_ sender: Any) {
        let (red, green, blue) = hsvToRgb(H: Double(HsvSliderH.value * 360), S: Double(HsvSliderS.value * 100), V: Double(HsvSliderV.value * 100))
        
        CmykTextFieldH.text = "\(Int(HsvSliderH.value * 360))"
        self.view.backgroundColor = UIColor(red: red/255, green: green/255, blue: blue/255, alpha: 1.0)
        UpdateRgbParams()
        UpdateCmykParams()
    }
    @IBAction func HsvSliderSAction(_ sender: Any) {
        let (red, green, blue) = hsvToRgb(H: Double(HsvSliderH.value * 360), S: Double(HsvSliderS.value * 100), V: Double(HsvSliderV.value * 100))
        
        CmykTextFieldS.text = "\(Int(HsvSliderS.value * 100))"
        self.view.backgroundColor = UIColor(red: red/255, green: green/255, blue: blue/255, alpha: 1.0)
        UpdateRgbParams()
        UpdateCmykParams()
    }
    @IBAction func HsvSliderVAction(_ sender: Any) {
        let (red, green, blue) = hsvToRgb(H: Double(HsvSliderH.value * 360), S: Double(HsvSliderS.value * 100), V: Double(HsvSliderV.value * 100))
        
        CmykTextFieldV.text = "\(Int(HsvSliderV.value * 100))"
        self.view.backgroundColor = UIColor(red: red/255, green: green/255, blue: blue/255, alpha: 1.0)
        UpdateRgbParams()
        UpdateCmykParams()
    }
    
    @IBAction func RgbTextFieldRAction(_ sender: Any) {
        RgbSliderRed.value = (Float(RgbTextFieldRed.text ?? "") ?? 0) / 255
        let color = UIColor(red: CGFloat(RgbSliderRed.value), green: CGFloat(RgbSliderGreen.value), blue: CGFloat(RgbSliderBlue.value), alpha: 1.0)
        self.view.backgroundColor = color
        UpdateCmykParams()
        UpdateHsvParams()
    }
    @IBAction func RgbTextFieldGAction(_ sender: Any) {
        RgbSliderGreen.value = (Float(RgbTextFieldGreen.text ?? "") ?? 0) / 255
        let color = UIColor(red: CGFloat(RgbSliderRed.value), green: CGFloat(RgbSliderGreen.value), blue: CGFloat(RgbSliderBlue.value), alpha: 1.0)
        self.view.backgroundColor = color
        UpdateCmykParams()
        UpdateHsvParams()
    }
    @IBAction func RgbTextFieldbAction(_ sender: Any) {
        RgbSliderBlue.value = (Float(RgbTextFieldBlue.text ?? "") ?? 0) / 255
        let color = UIColor(red: CGFloat(RgbSliderRed.value), green: CGFloat(RgbSliderGreen.value), blue: CGFloat(RgbSliderBlue.value), alpha: 1.0)
        self.view.backgroundColor = color
        UpdateCmykParams()
        UpdateHsvParams()
    }
    
    
    @IBAction func CmykTextFieldCAction(_ sender: Any) {
        let c = (Float(CmykTextFieldC.text ?? "") ?? 0) / 100
        CmykSliderC.value = c
        let (red, green, blue) = CMYKtoRGB(c: CGFloat(CmykSliderC.value), m: CGFloat(CmykSliderM.value), y: CGFloat(CmykSliderY.value), k: CGFloat(CmykSliderK.value))
        let color = UIColor(red: red, green: green, blue: blue, alpha: 1.0)
        self.view.backgroundColor = color
        UpdateRgbParams()
        UpdateHsvParams()
    }
    @IBAction func CmykTextFieldmAction(_ sender: Any) {
        let m = (Float(CmykTextFieldM.text ?? "") ?? 0) / 100
        CmykSliderM.value = m
        let (red, green, blue) = CMYKtoRGB(c: CGFloat(CmykSliderC.value), m: CGFloat(CmykSliderM.value), y: CGFloat(CmykSliderY.value), k: CGFloat(CmykSliderK.value))
        let color = UIColor(red: red, green: green, blue: blue, alpha: 1.0)
        self.view.backgroundColor = color
        UpdateRgbParams()
        UpdateHsvParams()
    }
    @IBAction func CmykTextFieldYAction(_ sender: Any) {
        let y = (Float(CmykTextFieldY.text ?? "") ?? 0) / 100
        CmykSliderY.value = y
        let (red, green, blue) = CMYKtoRGB(c: CGFloat(CmykSliderC.value), m: CGFloat(CmykSliderM.value), y: CGFloat(CmykSliderY.value), k: CGFloat(CmykSliderK.value))
        let color = UIColor(red: red, green: green, blue: blue, alpha: 1.0)
        self.view.backgroundColor = color
        UpdateRgbParams()
        UpdateHsvParams()
    }
    @IBAction func CmykTextFieldKAction(_ sender: Any) {
        let k = (Float(CmykTextFieldK.text ?? "") ?? 0) / 100
        CmykSliderK.value = k
        let (red, green, blue) = CMYKtoRGB(c: CGFloat(CmykSliderC.value), m: CGFloat(CmykSliderM.value), y: CGFloat(CmykSliderY.value), k: CGFloat(CmykSliderK.value))
        let color = UIColor(red: red, green: green, blue: blue, alpha: 1.0)
        self.view.backgroundColor = color
        UpdateRgbParams()
        UpdateHsvParams()
    }
    
    
    @IBAction func HsvTextFieldHAction(_ sender: Any) {
        let h = ((Float(CmykTextFieldH.text ?? "") ?? 0) / 100) / 3.6

        HsvSliderH.value = h
        let (red, green, blue) = hsvToRgb(H: Double(HsvSliderH.value * 360), S: Double(HsvSliderS.value * 100), V: Double(HsvSliderV.value * 100))
        
        self.view.backgroundColor = UIColor(red: red/255, green: green/255, blue: blue/255, alpha: 1.0)
        UpdateRgbParams()
        UpdateCmykParams()
    }
    
    @IBAction func HsvTextFieldSAction(_ sender: Any) {
        let s = ((Float(CmykTextFieldS.text ?? "") ?? 0) / 100)
        HsvSliderS.value = s
        let (red, green, blue) = hsvToRgb(H: Double(HsvSliderH.value * 360), S: Double(HsvSliderS.value * 100), V: Double(HsvSliderV.value * 100))
       
        self.view.backgroundColor = UIColor(red: red/255, green: green/255, blue: blue/255, alpha: 1.0)
        UpdateRgbParams()
        UpdateCmykParams()
    }
    
    @IBAction func HsvTextFieldVAction(_ sender: Any) {
        let v = ((Float(CmykTextFieldV.text ?? "") ?? 0) / 100)
        HsvSliderV.value = v
        let (red, green, blue) = hsvToRgb(H: Double(HsvSliderH.value * 360), S: Double(HsvSliderS.value * 100), V: Double(HsvSliderV.value * 100))
        
        self.view.backgroundColor = UIColor(red: red/255, green: green/255, blue: blue/255, alpha: 1.0)
        UpdateRgbParams()
        UpdateCmykParams()
    }
    
    
    
    @IBAction func ColorPickerAction(_ sender: Any) {
        // Initializing Color Picker
        let picker = UIColorPickerViewController()

        // Setting the Initial Color of the Picker
        picker.selectedColor = self.view.backgroundColor!

        // Setting Delegate
        picker.delegate = self
        
        // Presenting the Color Picker
        self.present(picker, animated: true, completion: nil)
    }
    
    
    func RGBtoCMYK(r : CGFloat, g : CGFloat, b : CGFloat) -> (c : CGFloat, m : CGFloat, y : CGFloat, k : CGFloat) {

        if r==0 && g==0 && b==0 {
            return (0, 0, 0, 1)
        }
        var c = 1 - r
        var m = 1 - g
        var y = 1 - b
        let minCMY = min(c, m, y)
        c = (c - minCMY) / (1 - minCMY)
        m = (m - minCMY) / (1 - minCMY)
        y = (y - minCMY) / (1 - minCMY)
        return (c, m, y, minCMY)
    }
    
    func CMYKtoRGB(c : CGFloat, m : CGFloat, y : CGFloat, k : CGFloat) -> (r : CGFloat, g : CGFloat, b : CGFloat) {
        let r = (1 - c) * (1 - k)
        let g = (1 - m) * (1 - k)
        let b = (1 - y) * (1 - k)
        return (r, g, b)
    }
    
    
    
    func rgbToHsv(r:CGFloat, g:CGFloat, b:CGFloat) -> (h:CGFloat, s:CGFloat, v:CGFloat){

        
        let cmax:CGFloat = max(r, max(g, b))
        let cmin:CGFloat = min(r, min(g, b))
        let diff = cmax - cmin
        var h:CGFloat = -1.0
        var s:CGFloat = -1.0
        
        if (cmax == cmin) {
            h = 0
        } else if (cmax == r) {
            let a = (g - b) / diff
            let b = (60 * a + 360)
            let intDev = Int(b / 360)
            h = b - CGFloat(CGFloat(intDev) * 360.0)
        }
        else if (cmax == g) {
            let a = (b - r) / diff
            let b = (60 * a + 120)
            let intDev = Int(b / 360)
            h = b - CGFloat(CGFloat(intDev) * 360.0)
        } else if (cmax == b) {
            let a = (r - g) / diff
            let b = (60 * a + 240)
            let intDev = Int(b / 360)
            h =  b - CGFloat(CGFloat(intDev) * 360.0)
        }
        
        if (cmax == 0) {
            s = 0
        } else {
            s = (diff / cmax) * 100
        }
        
        let v = cmax * 100;
        
        return (h, s, v)
    }
    
    func hsvToRgb(H: Double, S: Double, V: Double) -> (r: CGFloat, g: CGFloat, b: CGFloat) {
        
        if(H > 360 || H < 0 || S > 100 || S < 0 || V > 100 || V < 0){
                print("The givem HSV values are not in valid range")
        }
        
        let s = S/100
        let v = V/100
        let C = s*v
        let X = C*(1 - abs(fmod(H/60.0, 2)-1))
        let m = v-C
        var r = 0.0, g = 0.0, b = 0.0
        if (H >= 0 && H < 60) {
            r = C; g = X; b = 0;
        } else if(H >= 60 && H < 120) {
            r = X; g = C; b = 0;
        } else if(H >= 120 && H < 180) {
            r = 0; g = C; b = X;
        } else if(H >= 180 && H < 240) {
            r = 0; g = X; b = C;
        } else if(H >= 240 && H < 300) {
            r = X; g = 0; b = C;
        } else {
            r = C; g = 0; b = X;
        }
        
        let R = (r+m)*255
        let G = (g+m)*255
        let B = (b+m)*255
        
        return (CGFloat(R), CGFloat(G), CGFloat(B))
    }
}

extension ViewController: UIColorPickerViewControllerDelegate {
    
    //  Called once you have finished picking the color.
    func colorPickerViewControllerDidFinish(_ viewController: UIColorPickerViewController) {
        self.view.backgroundColor = viewController.selectedColor
        
        //self.RgbSliderRed.value = Float(self.view.backgroundColor!.red / 255)
//        let red = self.view.backgroundColor!.red
//        let green = self.view.backgroundColor!.green
//        let blue = self.view.backgroundColor!.blue
//        self.RgbTextFieldRed.text = "\(Int(red * 255))"
//        self.RgbTextFieldGreen.text = "\(Int(green * 255))"
//        self.RgbTextFieldBlue.text = "\(Int(blue * 255))"
//        self.RgbSliderRed.value = Float(red)
//        self.RgbSliderGreen.value = Float(green)
//        self.RgbSliderBlue.value = Float(blue)
        
//        let (c, m, y, k) = RGBtoCMYK(r: red, g: green, b: blue)
//
//        self.CmykTextFieldC.text = "\(Int(c * 100))"
//        self.CmykTextFieldM.text = "\(Int(m * 100))"
//        self.CmykTextFieldY.text = "\(Int(y * 100))"
//        self.CmykTextFieldK.text = "\(Int(k * 100))"
//        self.CmykSliderC.value = Float(c)
//        self.CmykSliderM.value = Float(m)
//        self.CmykSliderY.value = Float(y)
//        self.CmykSliderK.value = Float(k)
        UpdateRgbParams()
        UpdateCmykParams()
        UpdateHsvParams()
    }
    
    //  Called on every color selection done in the picker.
    func colorPickerViewControllerDidSelectColor(_ viewController: UIColorPickerViewController) {
           // self.view.backgroundColor = viewController.selectedColor
    }
}

extension UIColor {
    var red: CGFloat{ return self.cgColor.components![0] }
    var green: CGFloat{ return self.cgColor.components![1] }
    var blue: CGFloat{ return self.cgColor.components![2] }
    var alpha: CGFloat{ return self.cgColor.components![3] }
}

