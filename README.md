# SAP RFC SNC Client to get a SAP Logon Ticket for External ID

This Java Demo program is based on the SAP Community Blogs:

- [Setup data encryption between RFC Client and Web AS ABAP with SNC](https://blogs.sap.com/2006/09/29/setup-data-encryption-between-rfc-client-and-web-as-abap-with-snc/)
- [Single Sign On with External ID implemented in Ruby](https://blogs.sap.com/2006/09/30/single-sign-on-with-external-id-implemented-in-ruby/)

You can get the required components at:

- [SAP Cryptographic Library](https://launchpad.support.sap.com/#/softwarecenter/template/products/%20_APP=00200682500000001943&_EVENT=DISPHIER&HEADER=Y&FUNCTIONBAR=N&EVENT=TREE&NE=NAVIGATE&ENR=01200615320100002625&V=MAINT&TA=ACTUAL&PAGE=SEARCH/SAPCRYPTOLIB
)
- [SAP Java Connector (JCo)](https://launchpad.support.sap.com/#/softwarecenter/template/products/%20_APP=00200682500000001943&_EVENT=DISPHIER&HEADER=Y&FUNCTIONBAR=N&EVENT=TREE&NE=NAVIGATE&ENR=01200314690100001183&V=MAINT&TA=ACTUAL&PAGE=SEARCH/SAP%20JCO)

To detect if the user session is using SNC you can either use transaction SM04, select the session and choose User -> Technical Info. 
In the resulting list you should find an entry:

snc_count	1

As an alternative you can install [Report ZSM04000_SNC / ZRSUSR000_620 – Show SNC status of current user sessions](https://blogs.sap.com/2013/09/30/report-zsm04000snc-show-snc-status-of-current-user-sessions/).
