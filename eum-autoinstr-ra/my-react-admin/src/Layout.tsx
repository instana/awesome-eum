import React, { HtmlHTMLAttributes } from 'react';
import { CssBaseline, Container } from '@mui/material';
import { CoreLayoutProps, Error } from 'react-admin';
import { ErrorBoundary } from 'react-error-boundary';

import Header from './Header';

const Layout = ({ children }: LayoutProps) => (
  <>
    <CssBaseline />
    <Header />
    <Container sx={{ maxWidth: { xl: 1280 } }}>
      <main id="main-content">
        {/* @ts-ignore */}
        <ErrorBoundary FallbackComponent={Error}>{children}</ErrorBoundary>
      </main>
    </Container>
  </>
);

export interface LayoutProps extends CoreLayoutProps, Omit<HtmlHTMLAttributes<HTMLDivElement>, 'title'> {}

export default Layout;
