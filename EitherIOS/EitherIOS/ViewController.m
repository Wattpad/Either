//
//  ViewController.m
//  EitherIOS
//
//  Created by R. Tony Goold on 2015-03-06.
//  Copyright (c) 2015 Wattpad. All rights reserved.
//

#import "ViewController.h"
// Need the proy
#import "WPUserServiceCppProxy.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.

    id<WPUserService> service = [WPUserServiceCppProxy getService];
    WPEither *either = [service getUser:1234LL];
    if (either.left) {
        WPError *error = either.left;
        // Handle the error
    }
    else {
        WPUser *user = either.right;
        // Do something with the user
    }
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
