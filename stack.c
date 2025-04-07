#include <stdio.h>
#include <string.h>
#include <stdlib.h>

struct stack {
    int n ;
    struct stack *next ;
} ;

struct stack push(struct stack *s, int n){
    struct stack t;
    t.n = n;
    t.next = malloc(sizeof(struct stack));
    t.next = s;
    return t;
}

int pop(struct stack *s){
    int i=s->n;
    struct stack *t = s->next;
    free(s->next);
    s->n=t->next->n;
    s->next=t->next->next;
    free(t);
    return i;
}