<?xml version = "1.0" encoding = "UTF-8"?>
<xsl:stylesheet exclude-result-prefixes="xsl xs  html"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml"
	xmlns:html="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema"
	 version="2.0">
	<xsl:output method="xml" omit-xml-declaration="no" indent="no"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"
		doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN" />

    <xsl:template match="*|@*">
	<xsl:element name="{local-name(.)}">
            <xsl:apply-templates select="*|@*|text()" />
        </xsl:element>
    </xsl:template>

    <xsl:template match="@*">
        <xsl:copy>
            <xsl:apply-templates select="*|@*|text()" />
        </xsl:copy>
    </xsl:template>

	<xsl:template match="/">
			<xsl:apply-templates select="*|@*|text()" />
	</xsl:template>


</xsl:stylesheet>
