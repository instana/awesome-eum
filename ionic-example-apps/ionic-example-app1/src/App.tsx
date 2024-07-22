import { IonApp, IonRouterOutlet, IonSplitPane, setupIonicReact } from '@ionic/react';
import { IonReactRouter } from '@ionic/react-router';
import { Redirect, Route } from 'react-router-dom';
import Menu from './components/Menu';
import '@ionic/react/css/core.css';
import '@ionic/react/css/normalize.css';
import '@ionic/react/css/structure.css';
import '@ionic/react/css/typography.css';
import '@ionic/react/css/padding.css';
import '@ionic/react/css/float-elements.css';
import '@ionic/react/css/text-alignment.css';
import '@ionic/react/css/text-transformation.css';
import '@ionic/react/css/flex-utils.css';
import '@ionic/react/css/display.css';
import '@ionic/react/css/palettes/dark.system.css';

import './theme/variables.css';
import { createBrowserHistory } from 'history';
import Home from './pages/Home';
import Errors from './pages/Errors';
import XHR from './pages/XHR';
import CustomEvent from './pages/CustomEvents';

const customHistory = createBrowserHistory();
customHistory.listen(location => {
  console.log('Set page to', location.pathname);
  //@ts-ignore
  ineum('page', location.pathname);
});

export interface AppPage {
  url: string;
  iosIcon: string;
  mdIcon: string;
  title: string;
  component: React.FC;
}

import { hammerOutline, hammerSharp, homeOutline, homeSharp, paperPlaneOutline, paperPlaneSharp, colorWandOutline, colorWandSharp } from 'ionicons/icons';

const appPages: AppPage[] = [
  {
    title: 'Home',
    url: '/folder/home',
    iosIcon: homeOutline,
    mdIcon: homeSharp,
    component: Home
  },
  {
    title: 'Errors',
    url: '/folder/errors',
    iosIcon: hammerOutline,
    mdIcon: hammerSharp,
    component: Errors
  },
  {
    title: 'XHR',
    url: '/folder/xhr',
    iosIcon: paperPlaneOutline,
    mdIcon: paperPlaneSharp,
    component: XHR
  },
  {
    title: 'Custom Events',
    url: '/folder/customEvent',
    iosIcon: colorWandOutline,
    mdIcon: colorWandSharp,
    component: CustomEvent
  }
];

setupIonicReact();

const App: React.FC = () => {
  return (
    <IonApp>
      <IonReactRouter history={customHistory}>
        <IonSplitPane contentId="main">
          <Menu appPages = { appPages }/>
          <IonRouterOutlet id="main">
            <Route path="/" exact={true}>
              <Redirect to="/folder/home" />
            </Route>
            { appPages.map((page, index) => {

                const pageComponent = page.component;

                return (

                <Route key={ index } path={ page.url } exact={ true } component={ pageComponent } />
                );
            })}
          </IonRouterOutlet>
        </IonSplitPane>
      </IonReactRouter>
    </IonApp>
  );
};

export default App;
