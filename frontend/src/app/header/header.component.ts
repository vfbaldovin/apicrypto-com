import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {MenubarModule} from 'primeng/menubar';
import {MenuItem} from 'primeng/api';
import { FlexLayoutModule } from '@angular/flex-layout';
import {BreakpointObserver, BreakpointState} from "@angular/cdk/layout";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  headerClass='web-navigation-drawer';

  constructor() {}

  toggleNav() {
    this.headerClass = (this.headerClass == 'web-navigation-drawer') ? 'web-navigation-drawer animating open' : 'web-navigation-drawer';
  }

  toggleSideNav() {
    this.headerClass = 'web-navigation-drawer';
  }
}
