General architecture and design of the system.

# AppMain and AppData #

AppData is the Vaadin Session.

# Navigation #

Navigation from page to page is performed by calling AppData.getApplication().showXYZView(), which in turn, replaces the "Window".

# Persistence #

In order to have entity relationships with JDO, entity keys must be defined ([more](https://developers.google.com/appengine/docs/java/datastore/jdo/creatinggettinganddeletingdata#Keys)).

Add your content here.  Format your content with:
  * Text in **bold** or _italic_
  * Headings, paragraphs, and lists
  * Automatic links to other wiki pages