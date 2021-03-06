<#include "macros.ftm" >
complexTypes/${complexType.name}.js
/* Autogenerated with Kurento Idl */

/*
 * (C) Copyright 2013-2014 Kurento (http://kurento.org/)
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 */

var checkType = require('../checkType');

/**
 * Media API for the Kurento Web SDK
 *
 * @module KwsMedia/complexTypes
 *
 * @copyright 2014 Kurento (http://kurento.org/)
 * @license LGPL
 */

/**
 * Checker for {@link KwsMedia/complexTypes~${complexType.name}}
 *
 * @param {String} key
 * @param {KwsMedia/complexTypes~${complexType.name}} value
 */
function check${complexType.name}(key, value)
{
<#switch complexType.typeFormat>
  <#case "ENUM">
  if(typeof value != 'string')
    throw SyntaxError(key+' param should be a String, not '+typeof value);
  if(!value.match('<@join sequence=complexType.values separator="|"/>'))
    throw SyntaxError(key+' param is not one of [<@join sequence=complexType.values separator="|"/>] ('+value+')');
  <#break>
  <#case "REGISTER">
    <#list complexType.properties as property>
  checkType('${property.type.name}', key+'.${property.name}', value.${property.name}${property.optional?string("", ", true")});
    </#list>
  <#break>
</#switch>
};


/**
 * ${complexType.doc}
 *
 * @typedef KwsMedia/complexTypes~${complexType.name}
 *
<#switch complexType.typeFormat>
  <#case "ENUM">
 * @type {'<@join sequence=complexType.values separator="'|'"/>'}
  <#break>
  <#case "REGISTER">
 * @type {Object}
    <#list complexType.properties as property>
 * @property {${property.type.name}} ${property.name} - ${property.doc}
    </#list>
  <#break>
</#switch>
 */


module.exports = check${complexType.name};
