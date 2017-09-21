

import {Component, Input} from "@angular/core";


/**
 * UI Component that represent a navbar
 * in the top of page with all sections
 * made using Bootstrap.
 */
@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html'
})
export class NavbarComponent {
  @Input() appTitle: string;

}
