export type Identifier = string | number;

export interface RaRecord<IdentifierType extends Identifier = Identifier> extends Record<string, any> {
  id: IdentifierType;
}
