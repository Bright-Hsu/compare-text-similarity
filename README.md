# compare-text-similarity
a tiny Java program for comparing text similarity

## 程序功能

本程序能够比较两个DOC文档的相似性，可以统计出两个文档之间中有多少相同字符，多少不同字符，并且能够统计出前10个高频字词。

## 实现环境

语言：Java

依赖包：使用了 poi-scratchpad 的jar包和相关依赖包，另外导入了poi-ooxml 的jar包以实现 .docx 文件的读取。 

## 详细描述

首先，创建File类来存储文件text1.doc和text2.doc（后文以文件a和b表示）的文件内容。然后使用ArrayList动态数组来根据需要存储各种类型的字词，如使用same保存a和b中相同的字词，diffa和diffb分别保存a和b中不同的字词（不重复）。至于相同与不同字词的分类保存，只需利用循环遍历字词，并加一个判断即可。
